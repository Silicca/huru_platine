package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.MoodDao;
import com.app.huru.dao.impl.MoodDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Mood;

import java.util.List;
/**
 * Service lié à la gestion des humeurs
 * */
public class MoodService {

    private MoodDao moodDao;

    public MoodService(Context context){

        this.moodDao = new MoodDaoImpl(Database.getInstance(context));
    }

    /**
     * Récupération d'un humeur par son nom
     * */
    public Mood getMoodByName(String name){

        return this.moodDao.getByName(name);

    }
    /**
     * Récupération de toutes les humeurs
     * */
    public List<Mood> getAllMoods(){
        return this.moodDao.getAll();
    }
    /**
     * Enregistrement d'une humeur
     * */
    public void save(Mood mood){

        this.moodDao.save(mood);
    }
}
