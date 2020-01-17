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

    public String getHobbieName(){
        return this.hobbieName;
    }

    public void setHobbieName(String hobbieName){
        this.hobbieName = hobbieName;
    }

}
