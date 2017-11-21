package com.androidprojects.esprit.ikotlin.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.List1_Adapter;
import com.androidprojects.esprit.ikotlin.webservices.UserProfileServices;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends ListActivity {
    FirebaseAuth auth;

    private static final String[] settingsContent = {
            "Sep_ACCOUNT",
            "Edit profile",
            "Change password",
            "Connected accounts",
            "Sep_SETTINGS",
            "Activity Feed",
            "Push notifications",
            "Language",
            "Sep_Sign out",
            "Sign out"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new List1_Adapter(this,settingsContent));
        setContentView(R.layout.activity_settings);
        auth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onListItemClick(ListView l, View v,int position,long id){
        switch (position){

            case 9:
                auth.signOut();
                //delete user from sqlite
                UserProfileServices.getInstance().logout(this);
                //clear backstack
                finishAffinity();
                //start intent
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        //Toast.makeText(this,l.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
    }

}
