package com.androidprojects.esprit.ikotlin.models;

/**
 * Created by Amal on 25/11/2017.
 */

public class Chapter {

    private String title;
    private String description;
    private int nbBadges;
    private int timeTocomplete;
    private int quizzId;
    private String content;


    public Chapter(String title, String description, int nbBadges, int timeTocomplete, int quizzId, String content) {
        this.title = title;
        this.description = description;
        this.nbBadges = nbBadges;
        this.timeTocomplete = timeTocomplete;
        this.quizzId = quizzId;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNbBadges() {
        return nbBadges;
    }

    public int getTimeTocomplete() {
        return timeTocomplete;
    }

    public int getQuizzId() {
        return quizzId;
    }



}
