package com.androidprojects.esprit.pralloid;

import com.androidprojects.esprit.pralloid.transform.LinearTransformer;

import com.androidprojects.esprit.pralloid.transform.Transformer;

class ParallaxViewInfo {

    final float factor;
    final Transformer interpolator;

    private int maxX;
    private int maxY;

    ParallaxViewInfo(float factor, Transformer transformer) {
        this.factor = factor;
        this.interpolator = transformer == null ? new LinearTransformer() : transformer;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}