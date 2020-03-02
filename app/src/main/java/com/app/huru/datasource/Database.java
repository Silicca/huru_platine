package com.app.huru.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.huru.model.Mood;

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

        String createTableHobbies = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_HOBBIES+
                "( id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);";

        String createTableMoods = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_MOODS+
                "(id INTEGER PRIMARY KEY NOT NULL, name VARCHAR(255) NOT NULL);";

        String createTableStats = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_STATS+
                "(id INTEGER PRIMARY KEY NOT NULL, moodId INTEGER NOT NULL, date VARCHAR(255) NOT NULL);";
        /**
         * Création des hobbies de base proposés par l'application
         */
        String insertIntoTableHobbies1 = "INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('0', 'Dessin');";
        String insertIntoTableHobbies2 = "INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('1', 'Musique');";
        String insertIntoTableHobbies3 = "INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('2', 'Photographie');";
        String insertIntoTableHobbies4 = "INSERT INTO "+ TABLE_NAME_HOBBIES+ "(id,name) VALUES('3', 'Cinéma');";

        /**
         * Création des humeurs proposées par l'application
         * */
        String insertIntoTableMood1 = "INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('0', 'Content(e)');";
        String insertIntoTableMood2 = "INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('1', 'Triste');";
        String insertIntoTableMood3 = "INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('2', 'En colère');";
        String insertIntoTableMood4 = "INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('3', 'Stressé(e)');";
        String insertIntoTableMood5 = "INSERT INTO "+ TABLE_NAME_MOODS+ "(id,name) VALUES('4', 'Fatigué(e)');";

        /**
         * Création des tables
         * */
        db.execSQL(createTableUsers);
        db.execSQL(createTableNotes);
        db.execSQL(createTableHobbies);
        db.execSQL(createTableMoods);
        db.execSQL(createTableStats);

        /**
         * Insertion des données
         * */
        db.execSQL(insertIntoTableHobbies1);
        db.execSQL(insertIntoTableHobbies2);
        db.execSQL(insertIntoTableHobbies3);
        db.execSQL(insertIntoTableHobbies4);

        db.execSQL(insertIntoTableMood1);
        db.execSQL(insertIntoTableMood2);
        db.execSQL(insertIntoTableMood3);
        db.execSQL(insertIntoTableMood4);
        db.execSQL(insertIntoTableMood5);

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
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
