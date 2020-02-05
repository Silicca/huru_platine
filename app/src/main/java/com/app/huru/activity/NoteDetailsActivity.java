package com.app.huru.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
import com.app.huru.model.Note;
import com.app.huru.service.NoteService;

public class NoteDetailsActivity extends AppCompatActivity implements ActivityGUI {

    private NoteService noteService;

    private Button modifyNoteButton;
    private Button saveNoteButton;

    private TextView noteDate;

    private EditText noteHours;
    private EditText noteMinutes;
    private EditText noteTitle;
    private EditText noteParticipants;
    private EditText notePlace;

    private Note note;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details_activity_layout);

        this.noteService = new NoteService(getApplicationContext());
        int id = getIntent().getExtras().getInt("noteId");
        this.note = this.noteService.getNote(id);
        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.noteDate = findViewById(R.id.noteDate);

        this.modifyNoteButton = findViewById(R.id.modifyNoteButton);
        this.saveNoteButton = findViewById(R.id.saveNoteButton);

        this.noteHours = findViewById(R.id.noteHours);
        this.noteMinutes = findViewById(R.id.noteMinutes);
        this.noteTitle = findViewById(R.id.noteTitle);
        this.noteParticipants = findViewById(R.id.noteParticipants);
        this.notePlace = findViewById(R.id.notePlace);

        this.noteDate.setText(this.note.getDate().replace(' ','/'));
        this.noteHours.setText(this.note.getHours().split(":")[0]);
        this.noteMinutes.setText(this.note.getHours().split(":")[1]);
        this.noteTitle.setText(this.note.getTitle());
        this.noteParticipants.setText(this.note.getParticipants());
        this.notePlace.setText(this.note.getPlace());
    }
}
