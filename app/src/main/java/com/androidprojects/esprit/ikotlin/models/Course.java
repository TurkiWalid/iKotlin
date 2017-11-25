package com.androidprojects.esprit.ikotlin.models;

import java.util.ArrayList;

/**
 * Created by Amal on 25/11/2017.
 */

public class Course {

    private ArrayList<Chapter> chaptersList;
    private String title;
    private String description;
    private int completedChaptersNb;
    private int badgesEarnedNb;
    private int timeToFinish;


    public Course(ArrayList<Chapter> chaptersList, String title, String description, int completedChaptersNb, int badgesEarnedNb, int timeToFinish) {
        this.chaptersList = chaptersList;
        this.title = title;
        this.description = description;
        this.completedChaptersNb = completedChaptersNb;
        this.badgesEarnedNb = badgesEarnedNb;
        this.timeToFinish = timeToFinish;
    }

    public ArrayList<Chapter> getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(ArrayList<Chapter> chaptersList) {
        this.chaptersList = chaptersList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompletedChaptersNb() {
        return completedChaptersNb;
    }

    public void setCompletedChaptersNb(int completedChaptersNb) {
        this.completedChaptersNb = completedChaptersNb;
    }

    public int getBadgesEarnedNb() {
        return badgesEarnedNb;
    }

    public void setBadgesEarnedNb(int badgesEarnedNb) {
        this.badgesEarnedNb = badgesEarnedNb;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(int timeToFinish) {
        this.timeToFinish = timeToFinish;
    }
}

