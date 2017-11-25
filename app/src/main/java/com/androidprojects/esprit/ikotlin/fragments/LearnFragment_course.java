package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.ChaptersList_Adapter;
import com.tn.amalhichri.library.Parallaxor;


public class LearnFragment_course extends Fragment {

    private int coursePosition;

    public static LearnFragment_course newInstance(int coursePosition) {
        Bundle args = new Bundle();
        args.putInt("coursePosition",coursePosition);
        LearnFragment_course fragment = new LearnFragment_course();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        coursePosition= getArguments().getInt("coursePosition");

        View v = inflater.inflate(R.layout.fragment_learn_course, container, false);
       LinearLayout courseInfoLayout = v.findViewById(R.id.courseInfoLayout);

       ScrollView scrollView =  v.findViewById(R.id.scroll_view);
       ChaptersList_Adapter adapter = new ChaptersList_Adapter(getContext(),coursePosition);

       //filling the scrollView
        for (int i = 0; i < adapter.getCount(); i++) {
            final LearnFragment_chapter goToFragment = LearnFragment_chapter.newInstance(i);
            View item = adapter.getView(i, null, null);
            ((LinearLayout)v.findViewById(R.id.chaptersHolder)).addView(item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction().replace(R.id.root_learFragment,goToFragment).addToBackStack(null).commit();
                }
            });
        }
        //parallax effect
        if (scrollView instanceof Parallaxor) {
            ((Parallaxor) scrollView).parallaxViewBy(courseInfoLayout, 0.5f);
        }
        return v;
    }

}
