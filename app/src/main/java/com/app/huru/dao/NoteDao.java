package com.app.huru.dao;

import com.app.huru.model.Note;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface décrivant les méthodes à utiliser pour l'accès aux données des notes de l'utilisateur
 * */
public interface NoteDao {

    List<Note> getAll();

    List<Note> getByDate(String date);

    void save(Note note);

    void remove(Integer noteId);

    void update(Note note);

    Note get(Integer noteId);
}
