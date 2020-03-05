package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.HobbieDao;
import com.app.huru.dao.impl.HobbieDaoImpl;
import com.app.huru.dao.impl.NoteDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Hobbie;

import java.util.List;
/**
 * Service lié à la gestion des hobbies
 * */
public class HobbieService {

    private HobbieDao hobbieDao;

    public HobbieService(Context context){

        this.hobbieDao = new HobbieDaoImpl(Database.getInstance(context));
    }
    /**
     * Récupération de tous les hobbies de l'utilisateur
     * */
    public List<Hobbie> getAllHobbies(){

        return this.hobbieDao.getAll();
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
    public Hobbie getHobbie(Integer id){ return this.hobbieDao.get(id); }
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
