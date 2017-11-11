package com.androidprojects.esprit.ikotlin.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidprojects.esprit.ikotlin.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /** disabling actionBar **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
