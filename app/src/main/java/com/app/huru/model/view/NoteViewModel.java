package com.app.huru.model.view;
/**
 * Représente le model contenant les données d'une note à afficher
 * */
public class NoteViewModel {

    private String title;
    private String hours;
    private String participants;
    private String location;

    public NoteViewModel(){

    }

    public NoteViewModel(String title, String hours, String participants, String location) {
        this.title = title;
        this.hours = hours;
        this.participants = participants;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
