package com.androidprojects.esprit.ikotlin.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidprojects.esprit.ikotlin.R;

public class ProfileSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        /*** disabling actionBar ****/
        getSupportActionBar().hide();
    }
}
