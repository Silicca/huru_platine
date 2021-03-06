package com.app.huru.model;
/**
 * Représente un hobbie de l'utilisateur
 * */
public class Hobbie {

    private int id;

    private String name;

    private Mood mood;

    public Hobbie(){
        //constructeur vide
    }


    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Mood getMood(){
        return this.mood;
    }

    public void setMood(Mood mood){
        this.mood = mood;
    }

}
