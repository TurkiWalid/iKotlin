package com.androidprojects.esprit.ikotlin.activities;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
    private ColorMatrixColorFilter filter;
    private ColorMatrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** will be used to change tab icons colors on select/deselect */
        matrix = new ColorMatrix();
        matrix.setSaturation(0);
        filter = new ColorMatrixColorFilter(matrix);

        /** setting the actionBar **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFB417\" face=\"serif\" face=\"serif\">" + "<b>Learn</b>" + "</font>")));
        getSupportActionBar().setElevation(0);


        /*** setting the tabsLayout ***/

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new HomePageTabs_Adapter(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);
        tablayout=(TabLayout)findViewById(R.id.tabsLayout);
        tablayout.setupWithViewPager(vpPager);
        tablayout.getTabAt(0).setIcon(R.drawable.ic_learn_tab0);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_share_tab1);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_compete_tab2);
        tablayout.getTabAt(3).setIcon(R.drawable.ic_connect_tab3);
        allTabIconsToDeselected(tablayout);

        /** change title in actionBar depending on tabSelected **/
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAB SELECTED:",String.valueOf(tablayout.getSelectedTabPosition()));
                tab.getIcon().clearColorFilter();
                switch(tablayout.getSelectedTabPosition()) {
                    case 0:
                        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFB417\" face=\"serif\">" + "<b>Learn</b>" + "</font>")));
                        break;
                    case 1:
                        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFB417\" face=\"serif\">" + "<b>Share</b>" + "</font>")));
                        break;
                    case 2:
                        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFB417\" face=\"serif\">" + "<b>Complete</b>" + "</font>")));
                        break;
                    case 3:
                        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFB417\" face=\"serif\">" + "<b>Connect</b>" + "</font>")));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(filter);
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


    /*** helper methods ***/
    private void allTabIconsToDeselected(TabLayout tablayout){
        for(int i=1;i<4;i++){
            tablayout.getTabAt(i).getIcon().setColorFilter(filter);
        }
    }
}
