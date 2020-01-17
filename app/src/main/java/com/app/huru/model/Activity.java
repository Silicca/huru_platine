package com.app.huru.model;
/**
 * Représente une activité proposée à réaliser
 * */
public class Activity {

    private String activityName;

    public Activity(){
        //constructeur vide
    }

    public Activity(String activityName){
        this.activityName = activityName;
    }

    public String getActivityName(){
        return this.activityName;
    }

    public void setActivityName(String activityName){
        this.activityName = activityName;
    }


}
