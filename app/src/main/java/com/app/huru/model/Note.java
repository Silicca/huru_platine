package com.app.huru.model;

import java.time.LocalDateTime;

/**
 *
 * Représente une note à réaliser dans l'emploi du temps de l'utilisateur
 *
 * */
public class Note {

    private Integer id;

    private String date;

    private String hours;

    private String description;

    private String participants;

    private String place;

    public Note(){
        //constructeur vide
    }

    public Note(String date, String hours, String description, String participants, String place){

        this.date = date;
        this.hours = hours;
        this.description = description;
        this.participants = participants;
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
