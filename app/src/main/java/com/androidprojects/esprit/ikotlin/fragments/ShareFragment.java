package com.androidprojects.esprit.ikotlin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.activities.SignupActivity;
import com.androidprojects.esprit.ikotlin.adapters.ShareList_Adapter;
import com.androidprojects.esprit.ikotlin.models.ForumPost;


public class ShareFragment extends Fragment {


    ShareList_Adapter adapter;
    // static data for test
    // to be filled from database
    ForumPost[] posts =new ForumPost[]{
            new ForumPost("Amal Hichri", SignupActivity.user.getPictureURL(),"How to build android app in 1 month",3,7,100),
            new ForumPost("Walid Turki",SignupActivity.user.getPictureURL(),"A9ra mobile !! :D",7,3,200),
            new ForumPost("Test user",SignupActivity.user.getPictureURL(),"Behi w mba3ed ??",6,1,800)
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_share, container, false);

        adapter=new ShareList_Adapter(getContext(),posts);
        ((ListView)v.findViewById(R.id.shareLv)).setAdapter(adapter);

        // enabling search filter

        ((TextView)v.findViewById(R.id.inputSearch_shareLv)).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
               adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        return v;
    }

}
