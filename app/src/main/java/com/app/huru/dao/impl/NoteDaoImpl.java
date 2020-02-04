package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.NoteDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Implémentation du DAO concernant les notes de l'utilisateur
 * */
public class NoteDaoImpl extends Database implements NoteDao {

    public NoteDaoImpl(Context ctx){
        super(ctx, "notes");

    }
    /**
     * Création de la table notes
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String script = "CREATE TABLE " + this.getTableName()+
                "( id INTEGER PRIMARY KEY NOT NULL, date VARCHAR(255), hours VARCHAR(255), description VARCHAR(255), participants VARCHAR(255), place VARCHAR(255) NOT NULL);";


        db.execSQL(script);
    }
    /**
     * Si la base de données change, nouvelle création de la table users
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + this.getTableName());

        onCreate(db);
    }

    @Override
    public List<Note> getAll() {

        String sql = "SELECT  * FROM " + this.getTableName();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Note> notes = new ArrayList<>();

        Note note = new Note();

        if (cursor.moveToFirst()) {

            do {

                note.setId(Integer.parseInt(cursor.getString(0)));

                note.setDate(cursor.getString(1));

                note.setHours(cursor.getString(2));

                note.setDescription(cursor.getString(3));

                note.setParticipants(cursor.getString(4));

                note.setPlace(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        return notes;
    }

    @Override
    public List<Note> getByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public void save(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("date", note.getDate());
        values.put("hours", note.getHours());
        values.put("description", note.getDescription());
        values.put("participants", note.getParticipants());
        values.put("place", note.getPlace());

        db.insert(this.getTableName(), null, values);

        db.close();
    }

    @Override
    public void remove(Integer noteId) {

    }

    @Override
    public void update(Note note) {

    }


}
