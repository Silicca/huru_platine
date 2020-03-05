package com.app.huru.model;
/**
 * Représente une activité proposée à réaliser
 * */
public class Activity {

    private int id;

    private String activityName;

    private int percentJoiceMin;

    private int percentJoiceMax;

    private int percentSadMin;

    private int percentSadMax;

    private int percentAngryMin;

    private int percentAngryMax;

    private int percentStressMin;

    private int percentStressMax;

    private int percentTiredMin;

    private int percentTiredMax;

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

    public int getPercentJoiceMin() {
        return percentJoiceMin;
    }

    public void setPercentJoiceMin(int percentJoiceMin) {
        this.percentJoiceMin = percentJoiceMin;
    }

    public int getPercentJoiceMax() {
        return percentJoiceMax;
    }

    public void setPercentJoiceMax(int percentJoiceMax) {
        this.percentJoiceMax = percentJoiceMax;
    }

    public int getPercentSadMin() {
        return percentSadMin;
    }

    public void setPercentSadMin(int percentSadMin) {
        this.percentSadMin = percentSadMin;
    }

    public int getPercentSadMax() {
        return percentSadMax;
    }

    public void setPercentSadMax(int percentSadMax) {
        this.percentSadMax = percentSadMax;
    }

    public int getPercentAngryMin() {
        return percentAngryMin;
    }

    public void setPercentAngryMin(int percentAngryMin) {
        this.percentAngryMin = percentAngryMin;
    }

    public int getPercentAngryMax() {
        return percentAngryMax;
    }

    public void setPercentAngryMax(int percentAngryMax) {
        this.percentAngryMax = percentAngryMax;
    }

    public int getPercentStressMin() {
        return percentStressMin;
    }

    public void setPercentStressMin(int percentStressMin) {
        this.percentStressMin = percentStressMin;
    }

    public int getPercentStressMax() {
        return percentStressMax;
    }

    public void setPercentStressMax(int percentStressMax) {
        this.percentStressMax = percentStressMax;
    }

    public int getPercentTiredMin() {
        return percentTiredMin;
    }

    public void setPercentTiredMin(int percentTiredMin) {
        this.percentTiredMin = percentTiredMin;
    }

    public int getPercentTiredMax() {
        return percentTiredMax;
    }

    public void setPercentTiredMax(int percentTiredMax) {
        this.percentTiredMax = percentTiredMax;
    }
}
