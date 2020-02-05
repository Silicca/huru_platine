package com.app.huru.model;

import java.io.Serializable;

/**
 *
 * Représente une note à réaliser dans l'emploi du temps de l'utilisateur
 *
 * */
public class Note implements Serializable {

    private int id;

    private String title;

    private String date;

    private String hours;

    private String participants;

    private String place;

    public Note(){
        //constructeur vide
    }

    public Note(String title, String date, String hours, String participants, String place){

        this.title = title;
        this.date = date;
        this.hours = hours;
        this.participants = participants;
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
