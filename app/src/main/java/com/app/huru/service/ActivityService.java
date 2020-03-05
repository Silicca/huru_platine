package com.app.huru.service;

import android.content.Context;
import android.util.Log;

import com.app.huru.dao.ActivityDao;
import com.app.huru.dao.StatsDao;
import com.app.huru.dao.impl.ActivityDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Activity;
import com.app.huru.model.Stats;
import com.app.huru.tools.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityService {

    private ActivityDao activityDao;

    private StatsService statsService;

    public ActivityService(Context context){

        this.activityDao = new ActivityDaoImpl(Database.getInstance(context));
        this.statsService = new StatsService(context);
    }

    public List<Activity> getActivities(){

        String date = DateFormatter.dateToString(new Date() );

        List<Activity> activities = this.activityDao.getActivities();
        List<Activity> resultActivities = new ArrayList<>();

        int joicePercent = this.statsService.getPercentOf("Content(e)", date);
        int sadPercent = this.statsService.getPercentOf("Triste", date);
        int angryPercent = this.statsService.getPercentOf("En colère", date);
        int stressPercent = this.statsService.getPercentOf("Stressé(e)", date);
        int tiredPercent = this.statsService.getPercentOf("Fatigué(e)", date);

        Log.v("p", joicePercent+" joice ");
        Log.v("p", sadPercent+" sad ");
        Log.v("p", angryPercent+" angry ");
        Log.v("p", stressPercent+" stress ");
        Log.v("p", tiredPercent+" tired");

        for(Activity activity : activities){

            Log.v("a", activity.getPercentJoiceMin()+"");
            Log.v("a", activity.getPercentJoiceMax()+"");
            Log.v("a", activity.getPercentSadMin()+"");
            Log.v("a", activity.getPercentSadMax()+"");
            Log.v("a", activity.getPercentAngryMin()+"");
            Log.v("a", activity.getPercentAngryMax()+"");
            Log.v("a", activity.getPercentStressMin()+"");
            Log.v("a", activity.getPercentStressMax()+"");
            Log.v("a", activity.getPercentTiredMin()+"");

            Log.v("a", activity.getPercentTiredMax()+"");

            if(this.checkJoice(activity, joicePercent)
                    && this.checkSad(activity, sadPercent)
                    && this.checkAngry(activity, angryPercent)
                    && this.checkStress(activity, stressPercent)
                    && this.checkTired(activity, tiredPercent))
            {

                resultActivities.add(activity);
            }

        }

        return  resultActivities;
    }

    public boolean checkJoice(Activity activity, int percent){

        return (activity.getPercentJoiceMin() <= percent && activity.getPercentJoiceMax() >= percent);
    }

    public boolean checkSad(Activity activity, int percent){

        return (activity.getPercentSadMin() <= percent && activity.getPercentSadMax() >= percent);
    }

    public boolean checkAngry(Activity activity, int percent){

        return (activity.getPercentAngryMin() <= percent && activity.getPercentAngryMax() >= percent);
    }

    public boolean checkStress(Activity activity, int percent){

        return (activity.getPercentStressMin() <= percent && activity.getPercentStressMax() >= percent);
    }

    public boolean checkTired(Activity activity, int percent){

        return (activity.getPercentTiredMin() <= percent && activity.getPercentTiredMax() >= percent);
    }



}
