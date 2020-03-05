package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.MoodDao;

import com.app.huru.datasource.Database;
import com.app.huru.model.Mood;

import java.util.ArrayList;
import java.util.List;

public class MoodDaoImpl implements MoodDao {

    private Database db;

    public MoodDaoImpl(Database db){
        this.db = db;
    }

    /**
     * Récupération d'une humeur ciblée par son id
     * */
    @Override
    public Mood get(int id) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS+" WHERE id='"+id+"'";

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Mood mood = new Mood();

        if (cursor.moveToFirst()){

            mood.setId(cursor.getInt(0));

            mood.setMoodName(cursor.getString(1));

        }

        cursor.close();

        return mood;
    }
    /**
     * Récupération d'une humeur ciblée par son nom
     * */
    @Override
    public Mood getByName(String name) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS+" WHERE name='"+name+"'";

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Mood mood = new Mood();

        if (cursor.moveToFirst()){

            mood.setId(cursor.getInt(0));

            mood.setMoodName(cursor.getString(1));

        }

        cursor.close();

        return mood;
    }
    /**
     * Enregistrement d'une humeur en base de données
     * */
    @Override
    public void save(Mood mood) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", mood.getMoodName());

        db.insert(Database.TABLE_NAME_MOODS, null, values);

    }
    /**
     * Récupération de toutes les humeurs enregistrées en base de données
     * */
    @Override
    public List<Mood> getAll() {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS;

        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Mood> moods = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                Mood mood = new Mood();

                mood.setId(cursor.getInt(0));

                mood.setMoodName(cursor.getString(1));

                moods.add(mood);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return moods;
    }
}
