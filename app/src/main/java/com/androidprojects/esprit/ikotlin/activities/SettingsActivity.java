package com.androidprojects.esprit.ikotlin.activities;

import android.app.ListActivity;
import android.os.Bundle;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.List1_Adapter;

public class SettingsActivity extends ListActivity {

    private static final String[] settingsContent = {
            "Sep_ACCOUNT",
            "Edit profile",
            "Change password",
            "Connected accounts",
            "Sep_SETTINGS",
            "Activity Feed",
            "Push notifications",
            "Language",
            "Sep_Sign out"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new List1_Adapter(this,settingsContent));
        setContentView(R.layout.activity_settings);
    }
}
