package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.HobbieDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Hobbie;
import com.app.huru.model.Mood;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du DAO concernant les hobbies de l'utilisateur
 * */
public class HobbieDaoImpl implements HobbieDao {

    private Database db;

    public HobbieDaoImpl(Database db){
        this.db = db;
    }

    /**
     * Récupération de tous les hobbies enregistrés en base de données
     * */
    @Override
    public List<Hobbie> getAll() {

        String sql = "SELECT * FROM " + Database.TABLE_NAME_HOBBIES;

        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Hobbie> hobbies = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {

                Mood mood = new Mood();

                Hobbie hobbie = new Hobbie();

                hobbie.setId(cursor.getInt(0));

                hobbie.setName(cursor.getString(1));

                mood.setId(cursor.getInt(2));

                hobbie.setMood(mood);

                hobbies.add(hobbie);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return hobbies;
    }
    /**
     * Enregistrement d'un hobbie en base de données
     * */
    @Override
    public void save(Hobbie hobbie) {
        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", hobbie.getName());
        values.put("moodId", hobbie.getMood().getId());

        db.insert(Database.TABLE_NAME_HOBBIES, null, values);


    }
    /**
     * Suppression d'un hobbie en base de données en utilisant son id
     * */
    @Override
    public void remove(Integer hobbieId) {
        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(Database.TABLE_NAME_HOBBIES, "id=?", new String[]{String.valueOf(hobbieId)});

    }
    /**
     * Mise à jour d'un hobbie en base de données
     * */
    @Override
    public void update(Hobbie hobbie) {

        ContentValues values = new ContentValues();

        values.put("name", hobbie.getName());
        values.put("moodId", hobbie.getMood().getId());
        SQLiteDatabase db = this.db.getWritableDatabase();

        db.update(Database.TABLE_NAME_HOBBIES, values, "id=?", new String[]{String.valueOf(hobbie.getId())});


    }
    /**
     * Sélection d'un hobbie ciblé par son id
     * */
    @Override
    public Hobbie get(Integer hobbieId) {

        String sql = "SELECT * FROM " + Database.TABLE_NAME_HOBBIES+" WHERE id="+hobbieId;

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Hobbie hobbie = new Hobbie();

        if (cursor.moveToFirst()) {

            do {

                Mood mood = new Mood();

                hobbie.setId(cursor.getInt(0));

                hobbie.setName(cursor.getString(1));

                mood.setId(cursor.getInt(2));

                hobbie.setMood(mood);

            } while (cursor.moveToNext());
        }


        cursor.close();

        return hobbie;
    }

}
