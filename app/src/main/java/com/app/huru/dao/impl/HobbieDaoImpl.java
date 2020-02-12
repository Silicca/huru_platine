package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.HobbieDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Hobbie;

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
     * Création de la table hobbies
     */

    @Override
    public List<Hobbie> getAll() {

        String sql = "SELECT * FROM " + Database.TABLE_NAME_HOBBIES;

        SQLiteDatabase db = this.db.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Hobbie> hobbies = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                Hobbie hobbie = new Hobbie();

                hobbie.setId(Integer.parseInt(cursor.getString(0)));

                hobbie.setName(cursor.getString(1));

                hobbies.add(hobbie);

            } while (cursor.moveToNext());
        }

        return hobbies;
    }

    @Override
    public void save(Hobbie hobbie) {
        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", hobbie.getName());

        db.insert(Database.TABLE_NAME_HOBBIES, null, values);

        db.close();
    }

    @Override
    public void remove(Integer noteId) {

    }

    @Override
    public void update(Hobbie note) {

    }

    @Override
    public Hobbie get(Integer hobbieId) {
        String sql = "SELECT * FROM " + Database.TABLE_NAME_HOBBIES+" WHERE id="+hobbieId;

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Hobbie hobbie = new Hobbie();

        if (cursor.moveToFirst()) {

            do {

                hobbie.setId(Integer.parseInt(cursor.getString(0)));

                hobbie.setName(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        return hobbie;
    }

}