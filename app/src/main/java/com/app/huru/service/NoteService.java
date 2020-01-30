package com.app.huru.service;

import android.content.Context;

import com.app.huru.dao.NoteDao;
import com.app.huru.dao.impl.NoteDaoImpl;
import com.app.huru.model.Note;

import java.util.List;

public class NoteService {

    private NoteDao noteDao;

    public NoteService(Context context){

        this.noteDao = new NoteDaoImpl(context);

    }


    public List<Note> getAllNotes(){

        return this.noteDao.getAll();
    }

    public void saveNote(Note note){

        this.noteDao.save(note);
    }
}
