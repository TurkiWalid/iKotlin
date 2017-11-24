package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.androidprojects.esprit.ikotlin.R;
import com.tn.amalhichri.library.Parallaxor;


public class LearnFragment_course extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learn_course, container, false);
       LinearLayout courseInfoLayout = v.findViewById(R.id.courseInfoLayout);
       ScrollView scrollView =  v.findViewById(R.id.scroll_view);
        if (scrollView instanceof Parallaxor) {
            ((Parallaxor) scrollView).parallaxViewBy(courseInfoLayout, 0.5f);
        }
        return v;
    }

}
