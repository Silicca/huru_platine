package com.app.huru.dao;


import com.app.huru.model.Hobbie;

import java.util.List;

/**
 * Interface décrivant les méthodes à utiliser pour l'accès aux données des hobbies de l'utilisateur
 * */
public interface HobbieDao {

    List<Hobbie> getAll();

    void save(Hobbie note);

    void remove(Integer noteId);

    void update(Hobbie note);

    Hobbie get(Integer noteId);
}
