package com.app.huru.model;
/**
 * Représente une activité proposée à réaliser
 * */
public class Activity {

    private int id;

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

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
