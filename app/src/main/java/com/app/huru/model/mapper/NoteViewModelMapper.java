package com.app.huru.model.mapper;

import com.app.huru.model.Note;
import com.app.huru.model.view.NoteViewModel;

/**
 * Classe permettant de convetir un objet Note en objet NoteViewModel
 * */
public abstract class NoteViewModelMapper {

    public static NoteViewModel map(Note note){

        NoteViewModel model = new NoteViewModel();
        model.setHours(note.getHours());
        model.setLocation(note.getPlace());
        model.setParticipants(note.getParticipants());
        model.setTitle(note.getTitle());

        return model;
    }
}
