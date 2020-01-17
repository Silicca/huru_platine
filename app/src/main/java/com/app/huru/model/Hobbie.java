package com.app.huru.model;
/**
 * Représente un hobbie de l'utilisateur
 * */
public class Hobbie {

    private String hobbieName;

    public Hobbie(){
        //constructeur vide
    }

    public Hobbie(String hobbieName){
        this.hobbieName = hobbieName;
    }

    public String getActivityName(){
        return this.hobbieName;
    }

    public void setActivityName(String hobbieName){
        this.hobbieName = hobbieName;
    }

}
