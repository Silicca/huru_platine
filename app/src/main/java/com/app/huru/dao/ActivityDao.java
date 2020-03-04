package com.app.huru.dao;

import com.app.huru.model.Activity;

import java.util.List;

/**
 * Interface décrivant les méthodes à utiliser pour l'accès aux données des activités à proposer
 * */
public interface ActivityDao {

    List<Activity> getActivities();
}
