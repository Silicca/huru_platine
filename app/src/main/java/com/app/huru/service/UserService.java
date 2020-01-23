package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.UserDao;
import com.app.huru.dao.impl.UserDaoImpl;
import com.app.huru.model.User;
/**
 * Service permettant d'effectuer différents traitement sur les données liées à l'utilisateur de l'application
 * */
public class UserService {

    private UserDao userDao;

    public UserService(Context context){

        this.userDao = new UserDaoImpl(context);

    }


    public User getUser(){

        return this.userDao.get();
    }

    public boolean userAlreadyExists(){

        return this.userDao.exists();
    }

    public void saveUser(String userName){

        User newUser = new User();

        newUser.setName(userName);

        this.userDao.save(newUser);
    }
}
