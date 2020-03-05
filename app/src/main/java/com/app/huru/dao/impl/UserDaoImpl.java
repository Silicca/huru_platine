package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.UserDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.User;

/**
 * Implémentation du DAO concernant l'utilisateur de l'application
 * */
public class UserDaoImpl implements UserDao {

    private Database db;

    public UserDaoImpl(Database db){
        this.db = db;
    }

    /**
     * Récupération de l'utilisateur enregistré en base de données
     * */
    @Override
    public User get() {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_USERS;

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        User user = new User();

        if (cursor.moveToFirst()) {

            do {

                user.setId(cursor.getInt(0));

                user.setName(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return user;
    }

    /**
     * Vérification si un utilisateur est déjà enregistré en base de données
     * */
    @Override
    public boolean exists() {

        SQLiteDatabase db = this.db.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + Database.TABLE_NAME_USERS;

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count != 0;
    }
    /**
     * Enregistrement d'un utilisateur en base de données
     * */
    @Override
    public void save(User user) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", user.getName());

        db.insert(Database.TABLE_NAME_USERS, null, values);

    }
}
