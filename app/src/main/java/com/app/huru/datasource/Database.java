package com.app.huru.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.huru.model.Mood;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Singleton utilisé par les implémentations des différents DAO.
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
    public static final String TABLE_NAME_HOBBIES = "hobbies";
    public static final String TABLE_NAME_MOODS = "moods";
    public static final String TABLE_NAME_STATS = "stats";
    public static final String TABLE_NAME_ACTIVITIES = "activities";

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

        List<String> queries = new ArrayList<>();

        /**
         * Création des tables de la base de données
         * */
        queries.add("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERS+ " ( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);");

        queries.add("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_NOTES+ " ( id INTEGER PRIMARY KEY NOT NULL, date VARCHAR(255) NOT NULL, hours VARCHAR(255) NOT NULL, title VARCHAR(255) NOT NULL, participants VARCHAR(255) NOT NULL, place VARCHAR(255) NOT NULL);");

        queries.add("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_HOBBIES+" ( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);");

        queries.add("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_MOODS+" (id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);");

        queries.add("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_STATS+"( id INTEGER PRIMARY KEY NOT NULL, moodId INTEGER NOT NULL, date VARCHAR(255) NOT NULL);");

        queries.add("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_ACTIVITIES+"( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL, percentJoiceMin INTEGER NOT NULL, percentJoiceMax INTEGER NOT NULL, percentSadMin INTEGER NOT NULL, percentSadMax INTEGER NOT NULL, percentAngryMin INTEGER NOT NULL, percentAngryMax INTEGER NOT NULL, percentStressMin INTEGER NOT NULL, percentStressMax INTEGER NOT NULL, percentTiredMin INTEGER NOT NULL, percentTiredMax INTEGER NOT NULL);");

        /**
         * Création des hobbies de base proposés par l'application
         */
        queries.add("INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('0', 'Dessin');");
        queries.add("INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('1', 'Musique');");
        queries.add("INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('2', 'Photographie');");
        queries.add("INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('3', 'Cinéma');");

        /**
         * Création des humeurs proposées par l'application
         * */
        queries.add("INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('0', 'Content(e)');");
        queries.add("INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('1', 'Triste');");
        queries.add("INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('2', 'En colère');");
        queries.add("INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('3', 'Stressé(e)');");
        queries.add("INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('4', 'Fatigué(e)');");


        /**
         * Création des activités proposées par l'application
         * */
        queries.add("INSERT INTO "+ TABLE_NAME_ACTIVITIES+ "(id, name, percentJoiceMin, percentJoiceMax, percentSadMin, percentSadMax, percentAngryMin, percentAngryMax, percentStressMin, percentStressMax, percentTiredMin, percentTiredMax) VALUES('0', 'Aller au cinéma', '20', '100', '0', '20', '0', '10', '0', '10', '0', '20');");
        queries.add("INSERT INTO "+ TABLE_NAME_ACTIVITIES+ "(id, name, percentJoiceMin, percentJoiceMax, percentSadMin, percentSadMax, percentAngryMin, percentAngryMax, percentStressMin, percentStressMax, percentTiredMin, percentTiredMax) VALUES('1', 'Lire un livre', '0', '40', '0', '10', '0', '5', '0', '5', '10', '40');");


        /**
         * Exécution des requêtes
         * */
        for(String query : queries){
            db.execSQL(query);
        }


    }
    /**
     * Si la base de données change, nouvelle création des tables
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HOBBIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACTIVITIES);

        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
