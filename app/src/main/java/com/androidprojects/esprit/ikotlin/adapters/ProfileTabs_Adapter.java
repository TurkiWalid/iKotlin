package com.androidprojects.esprit.ikotlin.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidprojects.esprit.ikotlin.fragments.BadgesFragment;
import com.androidprojects.esprit.ikotlin.fragments.CodesFragments;
import com.androidprojects.esprit.ikotlin.fragments.PostsFragment;
import com.androidprojects.esprit.ikotlin.fragments.SkillsFragment;

/**
 * Created by Amal on 11/11/2017.
 */

public class ProfileTabs_Adapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[]{"2\nCodes", "12\nPosts","3\nSkills","5\nBadges"};

    public ProfileTabs_Adapter(FragmentManager fm) {
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
                return new CodesFragments();
            case 1:
                return new PostsFragment();
            case 2:
                return new SkillsFragment();
            case 3:
                return new BadgesFragment();
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
