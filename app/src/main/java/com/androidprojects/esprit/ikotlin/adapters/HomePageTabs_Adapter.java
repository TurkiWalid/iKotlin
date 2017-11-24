package com.androidprojects.esprit.ikotlin.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidprojects.esprit.ikotlin.fragments.CompeteFragment;
import com.androidprojects.esprit.ikotlin.fragments.ConnectFragment;
import com.androidprojects.esprit.ikotlin.fragments.RootFragment_learn;
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
                return new RootFragment_learn(); // because to switch fragments inside a tab we need a root FrameLayout,
                                                // in which we load fragments in each time ( getFragmentManager.replce(root,newFrag) )
            case 1:
                return new ShareFragment();   // to change to root fragment share
            case 2:
                return new CompeteFragment();  // to change to root fragment compete
            case 3:
                return new ConnectFragment();  // to change to root fragment connect
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    /*@Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }*/
}
