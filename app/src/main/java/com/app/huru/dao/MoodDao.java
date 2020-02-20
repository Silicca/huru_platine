package com.app.huru.dao;

import com.app.huru.model.Mood;

import java.util.List;

/**
 * Interface décrivant les méthodes d'accès aux donées concernant l'humeur de l'utilisateur
 * */
public interface MoodDao {

    Mood getByName(String name);

    void save(Mood mood);

    List<Mood> getAll();
}
