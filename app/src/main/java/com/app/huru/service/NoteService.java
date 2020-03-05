package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.NoteDao;
import com.app.huru.dao.impl.NoteDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Note;

import java.util.List;
/**
 * Service lié à la gestion des notes
 * */
public class NoteService {

    private NoteDao noteDao;

    public NoteService(Context context){

        this.noteDao = new NoteDaoImpl(Database.getInstance(context));
    }
    /**
     * Récupération de toutes les notes de l'utilisateur
     * */
    public List<Note> getAllNotes(){

        return this.noteDao.getAll();
    }
    /**
     * Enregistrement d'une note
     * */
    public void saveNote(Note note){

        this.noteDao.save(note);
    }
    /**
     * Mise à jour d'une note
     * */
    public void updateNote(Note note){

        this.noteDao.update(note);
    }
    /**
     * Suppression d'un note ciblée par son id
     * */
    public void removeNote(Integer noteId){
        this.noteDao.remove(noteId);
    }
    /**
     * Récupération d'un note ciblée par son id
     * */
    public Note getNote(Integer id){
        return this.noteDao.get(id);
    }
    /**
     * Récupération de la liste des notes pour une date donnée
     * */
    public List<Note> getNotesByDate(String date){
        return this.noteDao.getByDate(date);
    }
}
