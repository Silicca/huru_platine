package com.app.huru.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 *
 * Super classe utilisée par les implémentations des différents DAO.
 * Permet aux DAO d'accèder aux méthodes de SQLiteOpenHelper.
 *
 * */
public abstract class Database extends SQLiteOpenHelper {

    /**
     * Nom et version de la base de données locale
     * */

    private static final String DB_NAME = "HURU_DATABASE";
    private static final int DB_VERSION = 1;
    /**
     * Nom de la table à utiliser
     * */
    private String tableName;
    /**
     * @param context objet Context à utiliser pour le SQLiteOpenHelper
     * @param tableName nom de la table à utiliser pour cette instance de Database
     * */
    public Database(@Nullable Context context, String tableName) {

        super(context, DB_NAME, null, DB_VERSION);
        this.tableName = tableName;
    }

    public String getTableName(){
        return this.tableName;
    }

}
