package com.app.huru.model;

import androidx.annotation.NonNull;

/**
 *
 * Représente l'utilisateur de l'application
 * */
public class User {

    public int id;

    public String name;

    public User(){

    }

    public User(int id, String name){
        this.id = id;
        this.name = name;
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
