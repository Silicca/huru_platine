package com.app.huru.dao;

import com.app.huru.model.Stats;

import java.util.List;

/**
 * Interface décrivant les méthodes permettant d'accéder aux statistiques de l'utilisateur
 * */
public interface StatsDao {

    List<Stats> getAll();

    List<Stats> getByDate(String date);

    void save(Stats stats);

    void remove(Integer statsId);
}
