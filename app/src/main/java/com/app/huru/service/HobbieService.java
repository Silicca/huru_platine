package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.HobbieDao;
import com.app.huru.dao.MoodDao;
import com.app.huru.dao.impl.HobbieDaoImpl;
import com.app.huru.dao.impl.MoodDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Hobbie;

import java.util.List;
/**
 * Service lié à la gestion des hobbies
 * */
public class HobbieService {

    private HobbieDao hobbieDao;
    private MoodDao moodDao;

    public HobbieService(Context context){

        this.hobbieDao = new HobbieDaoImpl(Database.getInstance(context));
        this.moodDao = new MoodDaoImpl(Database.getInstance(context));
    }
    /**
     * Récupération de tous les hobbies de l'utilisateur
     * */
    public List<Hobbie> getAllHobbies(){

        List<Hobbie> hobbies = this.hobbieDao.getAll();

        for(Hobbie hobbie : hobbies){

            hobbie.setMood(this.moodDao.get(hobbie.getMood().getId()));
        }

        return hobbies;
    }
    /**
     * Enregistrement d'un nouvel hobbie
     * */
    public void saveHobbie(Hobbie hobbie){

        this.hobbieDao.save(hobbie);
    }
    /**
     * Récupération d'un hobbie ciblé par son id
     * */
    public Hobbie getHobbie(Integer id){

        Hobbie hobbie =  this.hobbieDao.get(id);

        hobbie.setMood(this.moodDao.get(hobbie.getMood().getId()));

        return hobbie;
    }
    /**
     * Mise à jour d'un hobbie
     * */
    public void updateHobbie(Hobbie hobbie){

        this.hobbieDao.update(hobbie);
    }
    /**
     * Suppression d'un hobbie ciblé par son id
     * */
    public void removeHobbie(Integer hobbieId){
        this.hobbieDao.remove(hobbieId);
    }
}
