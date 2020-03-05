package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.ActivityDao;
import com.app.huru.dao.impl.ActivityDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Activity;
import com.app.huru.tools.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service lié au système de proposition d'activités
 * */
public class ActivityService {

    private ActivityDao activityDao;

    private StatsService statsService;

    public ActivityService(Context context){

        this.activityDao = new ActivityDaoImpl(Database.getInstance(context));
        this.statsService = new StatsService(context);
    }

    /**
     * Récupération des activités proposées en fonction de l'humeur de l'utilisateur
     * */
    public List<Activity> getActivities(){

        String date = DateFormatter.dateToString(new Date() );

        List<Activity> activities = this.activityDao.getActivities();
        List<Activity> resultActivities = new ArrayList<>();

        int joicePercent = this.statsService.getPercentOf("Content(e)", date);
        int sadPercent = this.statsService.getPercentOf("Triste", date);
        int angryPercent = this.statsService.getPercentOf("En colère", date);
        int stressPercent = this.statsService.getPercentOf("Stressé(e)", date);
        int tiredPercent = this.statsService.getPercentOf("Fatigué(e)", date);

        for(Activity activity : activities){


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
    /**
     * Vérification concernant le taux de joie de l'utilisateur en fonction de l'activité proposée
     * */
    public boolean checkJoice(Activity activity, int percent){

        return (activity.getPercentJoiceMin() <= percent && activity.getPercentJoiceMax() >= percent);
    }
    /**
     * Vérification concernant le taux de tristesse de l'utilisateur en fonction de l'activité proposée
     * */
    public boolean checkSad(Activity activity, int percent){

        return (activity.getPercentSadMin() <= percent && activity.getPercentSadMax() >= percent);
    }
    /**
     * Vérification concernant le taux de colère de l'utilisateur en fonction de l'activité proposée
     * */
    public boolean checkAngry(Activity activity, int percent){

        return (activity.getPercentAngryMin() <= percent && activity.getPercentAngryMax() >= percent);
    }
    /**
     * Vérification concernant le taux de stress de l'utilisateur en fonction de l'activité proposée
     * */
    public boolean checkStress(Activity activity, int percent){

        return (activity.getPercentStressMin() <= percent && activity.getPercentStressMax() >= percent);
    }
    /**
     * Vérification concernant le taux de fatigue de l'utilisateur en fonction de l'activité proposée
     * */
    public boolean checkTired(Activity activity, int percent){

        return (activity.getPercentTiredMin() <= percent && activity.getPercentTiredMax() >= percent);
    }



}
