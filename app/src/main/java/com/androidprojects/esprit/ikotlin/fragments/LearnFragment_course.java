package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.adapters.ChaptersList_Adapter;
import com.androidprojects.esprit.ikotlin.models.Chapter;
import com.tn.amalhichri.library.Parallaxor;


public class LearnFragment_course extends Fragment {


    // static data to be changed from DB later
    Chapter[] chapters= new Chapter[]{
            new Chapter("Chapter 1_ Kotlin for server side","chapter 1 description goes here",2,120,1,null),
            new Chapter("Chapter 2_ Kotlin for Android","chapter 2 description goes here",2,120,1,null),
            new Chapter("Chapter 3_ Kotlin for JavaScript","chapter 3 description goes here",2,120,1,null),
            new Chapter("Chapter 4_ Kotlin / Native","chapter 4 description goes here",2,120,1,null),
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learn_course, container, false);
       LinearLayout courseInfoLayout = v.findViewById(R.id.courseInfoLayout);
       ListView chaptersLv =  v.findViewById(R.id.chaptersLv);
       ScrollView scrollView = scrollView =  v.findViewById(R.id.scroll_view);
       chaptersLv.setAdapter(new ChaptersList_Adapter(getContext(),chapters));
        ((Parallaxor) scrollView).parallaxViewBy(courseInfoLayout, 0.5f);
        if (scrollView instanceof Parallaxor) {
            ((Parallaxor) scrollView).parallaxViewBy(courseInfoLayout, 0.5f);
        }
        return v;
    }

}
