package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.NoteDao;
import com.app.huru.dao.impl.NoteDaoImpl;
import com.app.huru.datasource.Database;
import com.app.huru.model.Note;

import java.util.List;

public class NoteService {

    private NoteDao noteDao;

    public NoteService(Context context){

        this.noteDao = new NoteDaoImpl(Database.getInstance(context));
    }


    public List<Note> getAllNotes(){

        return this.noteDao.getAll();
    }

    public void saveNote(Note note){

        this.noteDao.save(note);
    }

    public void updateNote(Note note){

        this.noteDao.update(note);
    }

    public void removeNote(Integer noteId){
        this.noteDao.remove(noteId);
    }

    public Note getNote(Integer id){
        return this.noteDao.get(id);
    }

    public List<Note> getNotesByDate(String date){
        return this.noteDao.getByDate(date);
    }
}
