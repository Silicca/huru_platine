package com.app.huru.model;
/**
 * Représente une humeur dans l'application
 * */
public class Mood {

    private String moodName;

    public Mood(){
        //constructeur vide
    }

    public Mood(String moodName){
        this.moodName = moodName;
    }

    public String getMoodName(){
        return this.moodName;
    }

    public void setMoodName(String moodName){
        this.moodName = moodName;
    }

}
