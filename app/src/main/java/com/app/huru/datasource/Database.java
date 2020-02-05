package com.app.huru.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 *
 * Super classe utilisée par les implémentations des différents DAO.
 * Permet aux DAO d'accèder aux méthodes de SQLiteOpenHelper.
 *
 * */
public class Database extends SQLiteOpenHelper {

    private static Database instance = null;
    /**
     * Nom et version de la base de données locale
     * */

    private static final String DB_NAME = "HURU_DATABASE";
    private static final int DB_VERSION = 1;
    /**
     * Nom des tables à utiliser
     * */
    public static final String TABLE_NAME_USERS = "users";
    public static final String TABLE_NAME_NOTES = "notes";

    /**
     * @param context objet Context à utiliser pour le SQLiteOpenHelper
     * */
    private Database(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    public static Database getInstance(Context context){

        if(instance == null){
            instance = new Database(context.getApplicationContext());
        }

        return instance;
    }
    /**
     * Création des tables
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableUsers = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERS+
                "( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);";

        String createTableNotes = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_NOTES+
                "( id INTEGER PRIMARY KEY NOT NULL, date VARCHAR(255) NOT NULL, hours VARCHAR(255) NOT NULL, title VARCHAR(255) NOT NULL, participants VARCHAR(255) NOT NULL, place VARCHAR(255) NOT NULL);";

        db.execSQL(createTableUsers);
        db.execSQL(createTableNotes);

    }
    /**
     * Si la base de données change, nouvelle création de la table users
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTES);

        onCreate(db);
    }

}
