package com.androidprojects.esprit.ikotlin.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.androidprojects.esprit.ikotlin.R;


public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

    private ViewFlipper viewFlipper;
    private CirclePageIndicator indicator;
    private Animation slideInRightAnimation;
    private Animation slideOutLeftAnimation;
    private Context context;
    Animation slideInLeftAnimation;
    Animation slideOutRightAnimation;

    public CustomGestureListener(Context context, ViewFlipper viewFlipper, CirclePageIndicator indicator) {
        this.context=context;
        this.viewFlipper = viewFlipper;
        this.indicator = indicator;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int displayedChild = viewFlipper.getDisplayedChild();
        int childCount = viewFlipper.getChildCount();
        float sensitvity = 50;
        if ((e1.getX() - e2.getX()) > sensitvity) {
            if (displayedChild == childCount - 1) {
                return false;
            } else {
                swipeLeft(viewFlipper);
            }
        } else if ((e2.getX() - e1.getX()) > sensitvity) {
            if (displayedChild <= 0) {
                return false;
            } else {
                swipeRight(viewFlipper);
                indicator.setCurrentDisplayedChild(displayedChild);
            }
        }
        return true;
    }
    public void swipeLeft(ViewFlipper viewFlipper) {
        slideInRightAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
        slideOutLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_left);
        slideInRightAnimation.setDuration(150);
        slideOutLeftAnimation.setDuration(150);
        viewFlipper.setInAnimation(slideInRightAnimation);
        viewFlipper.setOutAnimation(slideOutLeftAnimation);
        viewFlipper.showNext();
    }
    public void swipeRight(ViewFlipper viewFlipper) {
        slideInLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
        slideOutRightAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right);
        slideInLeftAnimation.setDuration(150);
        slideOutRightAnimation.setDuration(150);
        viewFlipper.setInAnimation(slideInLeftAnimation);
        viewFlipper.setOutAnimation(slideOutRightAnimation);
        viewFlipper.showPrevious();
    }

}
