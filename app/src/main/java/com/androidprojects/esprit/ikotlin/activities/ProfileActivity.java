package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.ProfileTabs_Adapter;
import com.androidprojects.esprit.ikotlin.models.User;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;

import org.json.JSONObject;

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


        /*** testing linkedin API ***/
        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,picture-url)";

        final APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        User user;
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                Log.d("LINKEDIN API",apiResponse.getResponseDataAsJson().toString());
                JSONObject O = apiResponse.getResponseDataAsJson();

            }

            @Override
            public void onApiError(LIApiError liApiError) {
                Log.d("LINKEDIN API","failure");
            }
        });
    }
}
