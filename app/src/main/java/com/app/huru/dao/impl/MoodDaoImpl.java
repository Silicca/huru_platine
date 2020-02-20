package com.app.huru.dao.impl;

import com.app.huru.dao.MoodDao;

import com.app.huru.datasource.Database;
import com.app.huru.model.Mood;

import java.util.List;

public class MoodDaoImpl implements MoodDao {

    private Database db;

    public MoodDaoImpl(Database db){
        this.db = db;
    }

    @Override
    public Mood getByName(String name) {
        return null;
    }

    @Override
    public void save(Mood mood) {

    }

    @Override
    public List<Mood> getAll() {
        return null;
    }
}
