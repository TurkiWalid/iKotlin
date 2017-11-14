package com.androidprojects.esprit.ikotlin.models;

/**
 * Created by Amal on 14/11/2017.
 */

public class User {

    private String firstName,lastName, pictureUrl,id;

    public User(String firstName, String lastName, String pictureUrl, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicUrl() {
        return pictureUrl;
    }

    public void setPicUrl(String picUrl) {
        this.pictureUrl = picUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
