package com.app.huru.model;

import androidx.annotation.NonNull;

/**
 *
 * Représente l'utilisateur de l'application
 * */
public class User {

    private int id;

    private String name;

    public User(){

     //Constructeur vide

    }

    public int getId(){

        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){

        return this.name;
    }

    @NonNull
    @Override
    public String toString() {
        return "User [ id="+this.id+", name="+this.name+"]";
    }
}
