package com.app.huru.dao;

import com.app.huru.model.User;

/**
 * Interface décrivant les méthodes à utiliser pour l'accès aux données de l'utilisateur de l'application
 * */
public interface UserDao {

    User get();

    boolean exists();

    void save(User user);
}
