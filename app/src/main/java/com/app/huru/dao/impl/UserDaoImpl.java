package com.app.huru.dao.impl;

import android.content.Context;
import android.database.Cursor;
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
    /**
     * Création de la table users
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String script = "CREATE TABLE " + this.getTableName()+
                "( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);";


        db.execSQL(script);
    }
    /**
     * Si la base de données change, nouvelle création de la table users
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + this.getTableName());

        onCreate(db);
    }

    @Override
    public User get() {

        String sql = "SELECT  * FROM " + this.getTableName();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        User user = new User();

        if (cursor.moveToFirst()) {

            do {

                user.setId(Integer.parseInt(cursor.getString(0)));

                user.setName(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        return user;
    }

    /**
     * Regarde si un utilisateur est déjà enregistré en base de données
     * */
    @Override
    public boolean exists() {

        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + this.getTableName();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count != 0;
    }
}
