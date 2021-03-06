package com.app.huru.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.huru.dao.NoteDao;
import com.app.huru.datasource.Database;
import com.app.huru.model.Note;
import java.util.ArrayList;
import java.util.List;
/**
 * Implémentation du DAO concernant les notes de l'utilisateur
 * */
public class NoteDaoImpl implements NoteDao {

    private Database db;

    public NoteDaoImpl(Database db){
        this.db = db;
    }

    /**
     * Récupération de toutes les notes enregistrées en base de données
     * */
    public List<Note> getAll() {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_NOTES;

        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        List<Note> notes = new ArrayList<>();



        if (cursor.moveToFirst()) {

            do {
                Note note = new Note();

                note.setId(cursor.getInt(0));

                note.setDate(cursor.getString(1));

                note.setHours(cursor.getString(2));

                note.setTitle(cursor.getString(3));

                note.setParticipants(cursor.getString(4));

                note.setPlace(cursor.getString(5));

                notes.add(note);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return notes;
    }

    /**
     * Récupération de la liste des notes en fonction d'une date
     * */
    @Override
    public List<Note> getByDate(String date) {

        String sql = "SELECT  * FROM " + Database.TABLE_NAME_NOTES+" WHERE date='"+date+"'";

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Note> notes = new ArrayList<>();


        if (cursor.moveToFirst()) {

            do {

                Note note = new Note();

                note.setId(cursor.getInt(0));

                note.setDate(cursor.getString(1));

                note.setHours(cursor.getString(2));

                note.setTitle(cursor.getString(3));

                note.setParticipants(cursor.getString(4));

                note.setPlace(cursor.getString(5));

                notes.add(note);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return notes;
    }
    /**
     * Enregistrement d'une note en base de données
     * */
    @Override
    public void save(Note note) {
        SQLiteDatabase db = this.db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("date", note.getDate());
        values.put("hours", note.getHours());
        values.put("title", note.getTitle());
        values.put("participants", note.getParticipants());
        values.put("place", note.getPlace());

        db.insert(Database.TABLE_NAME_NOTES, null, values);

    }
    /**
     * Suppression d'une note ciblée par son id
     * */
    @Override
    public void remove(Integer noteId) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(Database.TABLE_NAME_NOTES, "id=?", new String[]{String.valueOf(noteId)});

    }
    /**
     * Mise à jour d'une note en base de données
     * */
    @Override
    public void update(Note note) {

        ContentValues values = new ContentValues();

        values.put("date", note.getDate());
        values.put("hours", note.getHours());
        values.put("title", note.getTitle());
        values.put("participants", note.getParticipants());
        values.put("place", note.getPlace());

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.update(Database.TABLE_NAME_NOTES, values, "id=?", new String[]{String.valueOf(note.getId())});

    }
    /**
     * Récupération d'une note ciblée par son id
     * */
    @Override
    public Note get(Integer noteId) {
        String sql = "SELECT  * FROM " + Database.TABLE_NAME_NOTES+" WHERE id="+noteId;

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Note note = new Note();

        if (cursor.moveToFirst()) {

            do {

                note.setId(cursor.getInt(0));

                note.setDate(cursor.getString(1));

                note.setHours(cursor.getString(2));

                note.setTitle(cursor.getString(3));

                note.setParticipants(cursor.getString(4));

                note.setPlace(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return note;
    }


}
