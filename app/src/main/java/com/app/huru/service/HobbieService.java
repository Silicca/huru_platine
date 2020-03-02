package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.HobbieDao;
import com.app.huru.dao.impl.HobbieDaoImpl;
import com.app.huru.dao.impl.NoteDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Hobbie;

import java.util.List;

public class HobbieService {

    private HobbieDao hobbieDao;

    public HobbieService(Context context){

        this.hobbieDao = new HobbieDaoImpl(Database.getInstance(context));
    }

    public List<Hobbie> getAllHobbies(){

        return this.hobbieDao.getAll();
    }

    public void saveHobbie(Hobbie hobbie){

        this.hobbieDao.save(hobbie);
    }

    public Hobbie getHobbie(Integer id){ return this.hobbieDao.get(id); }

    public void updateHobbie(Hobbie hobbie){

        this.hobbieDao.update(hobbie);
    }

    public void removeHobbie(Integer hobbieId){
        this.hobbieDao.remove(hobbieId);
    }
}
