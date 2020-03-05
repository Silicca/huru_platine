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

    public int getPercentOf(String moodName,  String searchingDate){

            float res = 0.0f;

            int total = 0;
            int count = 0;

            List<Stats> stats;

            if(searchingDate == null){
                stats = this.getAllStats();
            }
            else{
                stats = this.getStatsByDate(searchingDate);
            }

            total = stats.size();

            if(total == 0){

                return (int) res;
            }

            for(Stats stat : stats){

                if(stat.getMoodId() == this.moodDao.getByName(moodName).getId()){
                    count ++;
                }
            }

            res = ( count / (float) total ) * 100;

            return (int) res;

    }

}
