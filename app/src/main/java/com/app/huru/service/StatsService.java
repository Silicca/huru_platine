package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.MoodDao;
import com.app.huru.dao.StatsDao;
import com.app.huru.dao.impl.MoodDaoImpl;
import com.app.huru.dao.impl.StatsDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Stats;

import java.util.List;

public class StatsService {

    private StatsDao statsDao;

    private MoodDao moodDao;

    public StatsService(Context context){

        this.statsDao = new StatsDaoImpl(Database.getInstance(context));

        this.moodDao = new MoodDaoImpl(Database.getInstance(context));
    }

    public List<Stats> getAllStats(){

        List<Stats> stats = this.statsDao.getAll();

        for(Stats stat : stats){
            stat.setMood(this.moodDao.get(stat.getMoodId()));
        }

        return stats;
    }

    public void saveStats(Stats stats){

        this.statsDao.save(stats);
    }

    public List<Stats> getStatsByDate(String date){

        List<Stats> stats = this.statsDao.getByDate(date);

        for(Stats stat : stats){
            stat.setMood(this.moodDao.get(stat.getMoodId()));
        }

        return stats;
    }

    public void removeStats(int id){
        
        this.statsDao.remove(id);
    }
}
