package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.handlers.DataBaseHandler;
import com.androidprojects.esprit.ikotlin.models.User;
import com.androidprojects.esprit.ikotlin.webservices.ServerCallbacks;
import com.androidprojects.esprit.ikotlin.webservices.UserProfileServices;
import com.github.florent37.materialtextfield.MaterialTextField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    public static User user;
    private FirebaseAuth auth;
    MaterialTextField usernameTxt, emailTxt, passwordTxt;
    UserProfileServices userprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /** disabling actionBar **/
        getSupportActionBar().hide();


        //get firebase instance
        auth=FirebaseAuth.getInstance();

        usernameTxt = findViewById(R.id.userNameTxt);
        emailTxt= findViewById(R.id.emailText_signup);
        passwordTxt= findViewById(R.id.pswText);


    }

/** ---------- Sign Up ---------------- ***/
    public void signUp(View v){
        final String username=usernameTxt.getEditText().getText().toString();
        final String email = emailTxt.getEditText().getText().toString();
        final String password = passwordTxt.getEditText().getText().toString();

        if (email.trim().isEmpty()){
            emailTxt.getEditText().setError("Required");
            return;
        }

        if (username.trim().isEmpty()){
            usernameTxt.getEditText().setError("Required");
            return;
        }

        if (password.trim().isEmpty()){
            passwordTxt.getEditText().setError("Required");
            return;
        }

        //authenticate user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (passwordTxt.getEditText().getText().toString().length() < 6) {
                                passwordTxt.getEditText().setError("Password length must be over 6 characters");
                            } else {
                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            String idUser=auth.getCurrentUser().getUid();
                            // save user to mysql database and start the next activity
                            UserProfileServices.getInstance().registerUserWebService(idUser,username,email,SignupActivity.this,new ServerCallbacks(){

                                @Override
                                public void onSuccess(JSONObject result) {
                                    //send email verification

                                    //clear backstack
                                    finishAffinity();
                                    //start intent
                                    User user;
                                    user=UserProfileServices.getInstance().get_user_from_json(result);
                                    DataBaseHandler.getInstance(SignupActivity.this).saveUser(user);
                                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                    finish();
                                }

                                @Override
                                public void onError(VolleyError result) {
                                    auth.getCurrentUser().delete();
                                    Toast.makeText(SignupActivity.this,"Error while registring please retry",Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onWrong(JSONObject result) {
                                    auth.getCurrentUser().delete();
                                    Toast.makeText(SignupActivity.this,"Error while registring please retry",Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }
                });


    }

/** ---------- LINKEDIN SIGNUP --------- **/


    /*** siging up  ***/
    public void signUpWithLinkedIn(View view){
        LISessionManager.getInstance(getApplicationContext())
                .init(this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        /*Toast.makeText(getApplicationContext(), "success" +
                                        LISessionManager
                                                .getInstance(getApplicationContext())
                                                .getSession().getAccessToken().toString(),
                                Toast.LENGTH_LONG).show();*/
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                      /* Toast.makeText(getApplicationContext(), "failed "
                                        + error.toString(),
                                Toast.LENGTH_LONG).show();*/
                    }
                }, true);


        /*** creating a user with the sigup data  ***/
        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,picture-url)";

        final APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                Log.d("LOG TEST",apiResponse.getResponseDataAsJson().toString());
                Gson gson = new Gson();
                user = gson.fromJson(apiResponse.getResponseDataAsJson().toString(),User.class);
               //Log.d("tesssst",user.toString());

            }

            @Override
            public void onApiError(LIApiError liApiError) {
                Log.d("LINKEDIN API","failure");
            }
        });
    }
    /*** asking for linkedin account info retreive permission ***/
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }
    /*** handle the respone from LinkedIn Android app
     * by calling LISessionManager and start new activity ***/
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        LISessionManager.getInstance(getApplicationContext())
                .onActivityResult(this,
                        requestCode, resultCode, data);
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        startActivity(intent);
    }

/** ---------- FACEBOOK SIGNUP --------- **/
public void signUpWithFacebook(View v){

}



}
