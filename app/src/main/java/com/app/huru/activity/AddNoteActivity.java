package com.app.huru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
import com.app.huru.model.Note;
import com.app.huru.service.NoteService;


/**
 * Représente l'activité concernant l'ajout d'une note
 * */
public class AddNoteActivity extends AppCompatActivity implements ActivityGUI {

    private Button saveNoteButton;

    private EditText noteHours;
    private EditText noteMinutes;
    private EditText noteTitle;
    private EditText noteParticipants;
    private EditText notePlace;

    private NoteService noteService;

    private String noteDate;

    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity_layout);

        this.noteService = new NoteService(getApplicationContext());

        this.noteDate = getIntent().getExtras().getString("date");


        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.saveNoteButton = findViewById(R.id.saveNoteButton);
        this.noteHours = findViewById(R.id.noteHours);
        this.noteMinutes = findViewById(R.id.noteMinutes);
        this.noteTitle = findViewById(R.id.noteTitle);
        this.noteParticipants = findViewById(R.id.noteParticipants);
        this.notePlace = findViewById(R.id.notePlace);

        this.saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noteService.saveNote(buildNote());

                finish();
            }
        });
    }

    private Note buildNote(){

        Note note = new Note();

        note.setDate(this.noteDate);
        note.setTitle(this.noteTitle.getText().toString());
        note.setHours(this.noteHours.getText().toString()+":"+this.noteMinutes.getText().toString());
        note.setParticipants(this.noteParticipants.getText().toString());
        note.setPlace(this.notePlace.getText().toString());

        return note;
    }
}
