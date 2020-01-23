package com.app.huru.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.UserDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.User;

/**
 * Implémentation du DAO concernant l'utilisateur de l'application
 * */
public class UserDaoImpl extends Database implements UserDao {


    public UserDaoImpl(Context ctx){
        super(ctx, "users");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public User get() {

        return null;
    }

    @Override
    public boolean exists() {

        return false;
    }
}
