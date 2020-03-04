package com.app.huru.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.ActivityDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl implements ActivityDao {

    private Database db;

    public ActivityDaoImpl(Database db){
        this.db = db;
    }

    @Override
    public List<Activity> getActivities() {

        String sql = "SELECT * FROM " + Database.TABLE_NAME_ACTIVITIES;

        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Activity> activities = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                Activity activity = new Activity();

                activity.setId(cursor.getInt(0));

                activity.setActivityName(cursor.getString(1));

                activities.add(activity);

            } while (cursor.moveToNext());
        }

        return activities;
    }
}
