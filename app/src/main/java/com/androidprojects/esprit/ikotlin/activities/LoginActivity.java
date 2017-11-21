package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.handlers.DataBaseHandler;
import com.androidprojects.esprit.ikotlin.models.User;
import com.androidprojects.esprit.ikotlin.utils.Configuration;
import com.androidprojects.esprit.ikotlin.webservices.ServerCallbacks;
import com.androidprojects.esprit.ikotlin.webservices.UserProfileServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    public static String IP;
    TextView emailText;
    TextView passwordText;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        IP= Configuration.IP;
        /*** disabling actionBar ****/
        getSupportActionBar().hide();

        emailText=(TextView) findViewById(R.id.emailText_login);
        passwordText=(TextView) findViewById(R.id.pswText);
        progressBar=(ProgressBar) findViewById(R.id.progressBarLogin) ;


        //get firebase instance
        auth=FirebaseAuth.getInstance();
    }

/** ---------- FIREBASE (and mysql) LOGIN --------- **/
public void login(View view){

    final String userEmail = emailText.getText().toString();
    final String userPassword=passwordText.getText().toString();

    if (userEmail.trim().isEmpty()){
        emailText.setError("Required");
        return;
    }

    if (userPassword.trim().isEmpty()){
        passwordText.setError("Required");
        return;
    }
    progressBar.setVisibility(View.VISIBLE);
    //login via firebase
  auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener
          (LoginActivity.this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
          if (!task.isSuccessful()) {
              progressBar.setVisibility(View.GONE);
              // there was an error
              if (userPassword.length() < 6) {
                  passwordText.setError("Password length must be over 6 characters");
              } else {
                  Toast.makeText(LoginActivity.this, "Invalid entries\nPlease retry..", Toast.LENGTH_LONG).show();
              }
          } else {

              //try logging via mysql
              String uid=auth.getCurrentUser().getUid();
              UserProfileServices.getInstance().markLoggedUserWebService(uid, LoginActivity.this, new ServerCallbacks() {
                  @Override
                  public void onSuccess(JSONObject result) {
                      User user;
                      user=UserProfileServices.getInstance().get_user_from_json(result);
                      DataBaseHandler.getInstance(LoginActivity.this).saveUser(user);
                      Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                      startActivity(intent);
                  }

                  @Override
                  public void onError(VolleyError result) {
                    auth.signOut();
                  }

                  @Override
                  public void onWrong(JSONObject result) {
                      auth.signOut();
                  }
              });
              finish();
          }
      }
  });
}

/** ---------- LINKEDIN LOGIN --------- **/
public void loginWithLinkedIn(View view){
Toast.makeText(this,"linkedclicked",Toast.LENGTH_SHORT).show();
}

/** ---------- FACEBOOK LOGIN --------- **/
public void loginWithFacebook(View view){

}
}
