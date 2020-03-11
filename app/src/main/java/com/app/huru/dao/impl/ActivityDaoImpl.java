package com.app.huru.dao.impl;

import android.content.ContentValues;
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

    /**
     * Récupération de toutes les activités en base de données
     * */
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

                activity.setPercentJoiceMin(cursor.getInt(2));

                activity.setPercentJoiceMax(cursor.getInt(3));

                activity.setPercentSadMin(cursor.getInt(4));

                activity.setPercentSadMax(cursor.getInt(5));

                activity.setPercentAngryMin(cursor.getInt(6));

                activity.setPercentAngryMax(cursor.getInt(7));

                activity.setPercentStressMin(cursor.getInt(8));

                activity.setPercentStressMax(cursor.getInt(9));

                activity.setPercentTiredMin(cursor.getInt(10));

                activity.setPercentTiredMax(cursor.getInt(11));

                activities.add(activity);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return activities;
    }

    @Override
    public void save(Activity activity) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", activity.getActivityName());
        values.put("percentJoiceMin", activity.getPercentJoiceMin());
        values.put("percentJoiceMax", activity.getPercentJoiceMax());
        values.put("percentSadMin", activity.getPercentSadMin());
        values.put("percentSadMax", activity.getPercentSadMax());
        values.put("percentAngryMin", activity.getPercentAngryMin());
        values.put("percentAngryMax", activity.getPercentAngryMax());
        values.put("percentStressMin", activity.getPercentStressMin());
        values.put("percentStressMax", activity.getPercentStressMax());
        values.put("percentTiredMin", activity.getPercentTiredMin());
        values.put("percentTiredMax", activity.getPercentTiredMax());

        db.insert(Database.TABLE_NAME_ACTIVITIES, null, values);
    }

    @Override
    public void removeByName(String activityName) {
        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(Database.TABLE_NAME_ACTIVITIES, "name=?", new String[]{activityName});
    }
}
