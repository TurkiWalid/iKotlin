package com.androidprojects.esprit.ikotlin.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidprojects.esprit.ikotlin.fragments.CompeteFragment;
import com.androidprojects.esprit.ikotlin.fragments.ConnectFragment;
import com.androidprojects.esprit.ikotlin.fragments.LearnFragment;
import com.androidprojects.esprit.ikotlin.fragments.ShareFragment;


public class HomePageTabs_Adapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[]{"Learn", "Share","Compete","Connect"};

    public HomePageTabs_Adapter(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LearnFragment();
            case 1:
                return new ShareFragment();
            case 2:
                return new CompeteFragment();
            case 3:
                return new ConnectFragment();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }
}
