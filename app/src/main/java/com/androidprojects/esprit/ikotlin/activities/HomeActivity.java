package com.androidprojects.esprit.ikotlin.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.HomePageTabs_Adapter;

public class HomeActivity extends AppCompatActivity {

    private HomePageTabs_Adapter myAdapter;
    private TabLayout tablayout;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** disabling actionBar **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*** setting the tabsLayout ***/
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new HomePageTabs_Adapter(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);
        tablayout=(TabLayout)findViewById(R.id.tabsLayout);
        tablayout.setupWithViewPager(vpPager);
        tablayout=(TabLayout) findViewById(R.id.tabsLayout);
        tablayout.getTabAt(0).setIcon(R.drawable.learn_icon);
        tablayout.getTabAt(1).setIcon(R.drawable.share_icon);
        tablayout.getTabAt(2).setIcon(R.drawable.compete_icon);
        tablayout.getTabAt(3).setIcon(R.drawable.connect_icon);
    }


    /*** menu [profile,notifs,settings] ***/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
