package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;


public class LearnFragment_chapter extends Fragment {


    private int chapterPosition;
    static int contentStringId;

    public static LearnFragment_chapter newInstance(int chapterPosition) {
        Bundle args = new Bundle();
        args.putInt("chapterPosition",chapterPosition);
        LearnFragment_chapter fragment = new LearnFragment_chapter();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        chapterPosition=getArguments().getInt("chapterPosition");

        View v = inflater.inflate(R.layout.fragment_learn_fragment_chapter, container, false);
        loadString(chapterPosition);
        ((TextView)v.findViewById(R.id.testContent)).setText(getContext().getString(contentStringId));
        return v;
    }

    private void loadString(int i){
        switch (i){
            case 0:
                contentStringId=R.string.course1chapter1Content;
                break;
            case 1:
                contentStringId=R.string.course1chapter2Content;
                break;
            case 2:
                contentStringId=R.string.course1chapter3Content;
                break;
            case 3:
                contentStringId=R.string.course1chapter4Content;
                break;
            default:
                break;
        }
    }
}
