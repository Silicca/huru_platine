package com.app.huru.model;
/**
 * Représente une humeur dans l'application
 * */
public class Mood {

    private int id;

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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
