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

    private String description;

    private String participants;

    private String lieu;

    public Note(){
        //constructeur vide
    }

    public Note(String date, String description, String participants, String lieu){

        this.date = date;
        this.description = description;
        this.participants = participants;
        this.lieu = lieu;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
