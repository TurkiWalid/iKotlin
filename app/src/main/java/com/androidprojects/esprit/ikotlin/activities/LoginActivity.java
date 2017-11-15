package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.utils.AppSingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static String IP;
    public static final String URL_LOGIN = "/IKotlin/web/user/authentification";
    TextView emailText;
    TextView passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        IP=getResources().getString(R.string.serviceIP);
        /*** disabling actionBar ****/
        getSupportActionBar().hide();

        emailText=(TextView) findViewById(R.id.emailText_login);
        passwordText=(TextView) findViewById(R.id.pswText);
    }

/** ---------- FIREBASE (or mysql?) LOGIN --------- **/
public void login(View view){

    String userEmail = emailText.getText().toString();
    String userPassword=passwordText.getText().toString();
    if(!userEmail.isEmpty() && !userPassword.isEmpty()) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("email", userEmail);
        m.put("password", userPassword);

        final JSONObject jsonBody = new JSONObject(m);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, IP + URL_LOGIN, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("userdata","responding...");
                        //Log.d("userdata",response.toString());
                        if (!response.has("Error"))
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        else
                            Toast.makeText(getBaseContext(), "Wrong entries...", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Connection problem", Toast.LENGTH_LONG).show();

                    }
                });
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,//timeout
                3,//retry
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppSingleton.getInstance(this).addToRequestQueue(jsObjRequest, "login");
    }
    else Toast.makeText(getBaseContext(), "Empty fields", Toast.LENGTH_LONG).show();
}

/** ---------- LINKEDIN LOGIN --------- **/
public void loginWithLinkedIn(View view){

}

/** ---------- FACEBOOK LOGIN --------- **/
public void loginWithFacebook(View view){

}
}
