package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.ProfileTabs_Adapter;
import com.androidprojects.esprit.ikotlin.handlers.DataBaseHandler;
import com.androidprojects.esprit.ikotlin.models.User;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ProfileTabs_Adapter adapter;
    private ViewPager viewPager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*** disabling actionBar ****/
        getSupportActionBar().hide();

        /** get user from sqlite**/
        user= DataBaseHandler.getInstance(getApplicationContext()).getUser();

        /** the tabLayout **/
        tabLayout = (TabLayout) findViewById(R.id.profileTabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager_profileTabs);
        adapter = new ProfileTabs_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabTextColors(16757783,5526612);

        /*** profile settings click ***/
        findViewById(R.id.profileSettingsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),ProfileSettingsActivity.class));
            }
        });


        if(user!=null){
            /** fields data **/
            ((TextView) findViewById(R.id.fullNameInProfile)).setText((user.getUsername().isEmpty()?user.getFirstName():user.getUsername()));
            Log.i("user",user.toString());
            if(user.getPictureURL()!=null){
                ((ImageView)findViewById(R.id.userImgProfile)).setImageURI(Uri.parse(user.getPictureURL()));
            }
            else
                Picasso.with(getApplicationContext()).load(R.drawable.user_empty_profile_picture).into((ImageView)findViewById(R.id.userImgProfile));

        }

    }
}
