package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.models.User;
import com.google.gson.Gson;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public class SignupActivity extends AppCompatActivity {

    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /** disabling actionBar **/

        getSupportActionBar().hide();

    }

/** ---------- Sign Up ---------------- ***/
    public void signUp(View v){

    }

/** ---------- LINKEDIN SIGNUP --------- **/


    /*** siging up  ***/
    public void signUpWithLinkedIn(View view){
        LISessionManager.getInstance(getApplicationContext())
                .init(this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {

                        Toast.makeText(getApplicationContext(), "success" +
                                        LISessionManager
                                                .getInstance(getApplicationContext())
                                                .getSession().getAccessToken().toString(),
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onAuthError(LIAuthError error) {

                        Toast.makeText(getApplicationContext(), "failed "
                                        + error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }, true);


        /*** creating a user with the sigup data  ***/
        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,picture-url)";

        final APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());


        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                Gson gson = new Gson();
                user = gson.fromJson(apiResponse.getResponseDataAsJson().toString(),User.class);
                Log.d("tesssst",user.toString());
                // supposed to add this user to db
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
