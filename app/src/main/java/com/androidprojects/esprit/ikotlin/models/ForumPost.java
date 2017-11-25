package com.androidprojects.esprit.ikotlin.models;

/**
 * Created by Amal on 24/11/2017.
 */

public class ForumPost {

    private String postedBy;
    private String postedBy_picUrl;
            // these two attributes will be replaced by userId .. (depends on the dataBase)
    private String title;
    private int nbComments;
    private int postedSince;
    private int nbvotes;


    public ForumPost(String postedBy, String postedBy_picUrl, String title, int nbComments, int postedSince, int nbvotes) {
        this.postedBy = postedBy;
        this.postedBy_picUrl = postedBy_picUrl;
        this.title = title;
        this.nbComments = nbComments;
        this.postedSince = postedSince;
        this.nbvotes = nbvotes;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedBy_picUrl() {
        return postedBy_picUrl;
    }

    public void setPostedBy_picUrl(String postedBy_picUrl) {
        this.postedBy_picUrl = postedBy_picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getPostedSince() {
        return postedSince;
    }

    public void setPostedSince(int postedSince) {
        this.postedSince = postedSince;
    }

    public int getNbvotes() {
        return nbvotes;
    }

    public void setNbvotes(int nbvotes) {
        this.nbvotes = nbvotes;
    }
}
