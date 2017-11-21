package com.androidprojects.esprit.ikotlin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.utils.CirclePageIndicator;
import com.androidprojects.esprit.ikotlin.utils.CustomGestureListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity implements GestureDetector.OnDoubleTapListener, View.OnClickListener  {

    private ViewFlipper vf;
    private CirclePageIndicator circlePageIndicator;
    private GestureDetector gestureDecirclePageIndicator;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** hiding the satutus bar **/
        //getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN);

        /** loading menu_main ui **/
        setContentView(R.layout.activity_main);

        /** check if user is connected **/
        auth=FirebaseAuth.getInstance();
            if(auth.getCurrentUser()!=null){
                //clear backstack
                finishAffinity();
                //start intent
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish(); // call this to finish the current activity
            }

        /*** Set the flipper view ***/
        vf = findViewById(R.id.vwFlp);
        circlePageIndicator = findViewById(R.id.circlePageIndicator);
        circlePageIndicator.setViewFlipper(vf);
        gestureDecirclePageIndicator = new GestureDetector(this, new CustomGestureListener(getApplicationContext(),vf, circlePageIndicator));
    }



    /*** viewFliper scrolling onDoubleTapListener ***/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDecirclePageIndicator.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    /*** login and signup buttons clicks ***/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.loginBtn):
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case (R.id.signupBtn):
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
        }
    }



}
