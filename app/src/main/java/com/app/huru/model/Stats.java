package com.app.huru.model;

/**
 * Représente des données concernant les statistiques de l'utilisateur à un moment donné
 * */
public class Stats {

    private int id;

    private int moodId;

    private String date;

    public Stats(){
        //constructeur vide
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoodId() {
        return this.moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
