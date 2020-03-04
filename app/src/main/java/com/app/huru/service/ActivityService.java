package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.ActivityDao;
import com.app.huru.dao.impl.ActivityDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Activity;

import java.util.List;

public class ActivityService {

    private ActivityDao activityDao;

    public ActivityService(Context context){

        this.activityDao = new ActivityDaoImpl(Database.getInstance(context));
    }

    public List<Activity> getActivities(){
        return this.activityDao.getActivities();
    }
}
