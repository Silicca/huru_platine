package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.StatsDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Stats;

import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl implements StatsDao {

    private Database db;

    public StatsDaoImpl(Database db){
        this.db = db;
    }

    @Override
    public List<Stats> getAll() {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_STATS;

        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Stats> stats = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {

                Stats stat = new Stats();

                stat.setId(cursor.getInt(0));

                stat.setMoodId(cursor.getInt(1));

                stat.setDate(cursor.getString(2));

                stats.add(stat);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return stats;
    }

    @Override
    public List<Stats> getByDate(String date) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_STATS+" WHERE date LIKE '%"+date+"'";

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Stats> stats = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {

                Stats stat = new Stats();

                stat.setId(cursor.getInt(0));

                stat.setMoodId(cursor.getInt(1));

                stat.setDate(cursor.getString(2));

                stats.add(stat);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return stats;
    }

    @Override
    public void save(Stats stats) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("moodId", stats.getMoodId());
        values.put("date", stats.getDate());

        db.insert(Database.TABLE_NAME_STATS, null, values);
    }

    @Override
    public void remove(Integer statsId) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(Database.TABLE_NAME_STATS, "id=?", new String[]{String.valueOf(statsId)});
    }
}
