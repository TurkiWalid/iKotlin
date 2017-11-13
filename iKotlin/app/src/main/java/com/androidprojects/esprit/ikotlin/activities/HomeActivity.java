package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.HomePageTabs_Adapter;

public class HomeActivity extends AppCompatActivity {

    private HomePageTabs_Adapter myAdapter;
    private TabLayout tablayout;
    private Menu menu;
    private FragmentManager fgManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** remove back btn from actionBar **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Learn");

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

        /** change title in actionBar depending on tabSelected **/
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAB SELECTED:",String.valueOf(tablayout.getSelectedTabPosition()));
                switch(tablayout.getSelectedTabPosition()) {
                    case 0:
                        getSupportActionBar().setTitle("Learn");
                        break;
                    case 1:
                        getSupportActionBar().setTitle("Share");
                        break;
                    case 2:
                        getSupportActionBar().setTitle("Compete");
                        break;
                    case 3:
                        getSupportActionBar().setTitle("Connect");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        /** fragment manager reference **/
        fgManager = getSupportFragmentManager();
    }


    /*** menu [profile,notifs,settings] ***/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_profile){
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        if(item.getItemId()==R.id.action_settings){
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
