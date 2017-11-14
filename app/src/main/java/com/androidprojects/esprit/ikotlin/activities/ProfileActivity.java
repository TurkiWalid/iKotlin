package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.ProfileTabs_Adapter;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ProfileTabs_Adapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*** disabling actionBar ****/
        getSupportActionBar().hide();

        /** the tabLayout **/
        tabLayout = (TabLayout) findViewById(R.id.profileTabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager_profileTabs);
        adapter = new ProfileTabs_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        /*** profile settings click ***/
        findViewById(R.id.profileSettingsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),ProfileSettingsActivity.class));
            }
        });

        /** fields data **/
        ((TextView) findViewById(R.id.fullNameInProfile)).setText(SignupActivity.user.getFirstName()+" "+SignupActivity.user.getLastName());
        //((ImageView)findViewById(R.id.userImgProfile)).setImageURI(Uri.parse("android.resource://com.androidprojects.esprit.ikotlin/"+R.drawable.tesimg));
        Picasso.with(getApplicationContext()).load(SignupActivity.user.getPicUrl()).into((ImageView)findViewById(R.id.userImgProfile));


    }
}
