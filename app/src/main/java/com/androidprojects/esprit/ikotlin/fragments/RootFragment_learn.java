package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidprojects.esprit.ikotlin.R;


public class RootFragment_learn extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_root_fragment_learn, container, false);
       getFragmentManager().beginTransaction().replace(R.id.root_learFragment,new LearnFragment()).addToBackStack(null).commit();
       return v;
    }

}
