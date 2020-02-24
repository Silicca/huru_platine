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

    @Override
    public Mood get(int id) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS+" WHERE id='"+id+"'";

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Mood mood = new Mood();

        if (cursor.moveToFirst()){

            mood.setId(cursor.getInt(0));

            mood.setMoodName(cursor.getString(1));

        }

        db.close();

        return mood;
    }

    @Override
    public Mood getByName(String name) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS+" WHERE name='"+name+"'";

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Mood mood = new Mood();

        if (cursor.moveToFirst()){

            mood.setId(cursor.getInt(0));

            mood.setMoodName(cursor.getString(1));

        }

        db.close();

        return mood;
    }

    @Override
    public void save(Mood mood) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", mood.getMoodName());

        db.insert(Database.TABLE_NAME_MOODS, null, values);

        db.close();

    }

    @Override
    public List<Mood> getAll() {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_MOODS;

        SQLiteDatabase db = this.db.getWritableDatabase();

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

        db.close();

        return moods;
    }
}
