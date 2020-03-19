package com.app.huru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.recyclerview.NoteViewAdapter;
import com.app.huru.model.Note;
import com.app.huru.service.NoteService;
import com.app.huru.tools.DateFormatter;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Activité affichant un calendrier et les notes de l'utilisateur
 * */
public class CalendarActivity extends AppCompatActivity implements ActivityGUI {

    private CalendarView calendar;

    private NoteViewAdapter noteViewAdapter;

    private NoteService noteService;

    private String date;
    private List<Note> notes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_layout);

        this.noteService = new NoteService(getApplicationContext());
        this.notes = new ArrayList<>();

        this.setupGUI();
    }

    @Override
    public void setupGUI() {



        FloatingActionButton addNoteButton = findViewById(R.id.addNoteButton);
        /**
         * Ajout du listener sur le clic du bouton permettant d'ajouter une note au jour sélectionné
         * */
        addNoteButton.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("date", date);
            startActivity(intent);

        });

        TextView selectedDate = findViewById(R.id.selectedDate);

        this.calendar = findViewById(R.id.calendar);
        /**
         * Mise en place du listener permettant de récupérer la date actuellement sélectionnée
         * */
        this.calendar.setOnDayClickListener(eventDay -> {

            date = DateFormatter.eventDayToString(eventDay);

            selectedDate.setText(date.replace(' ', '/'));
            updateNotesList();
        });
        /**
         * Initialisation du calendrier en lui indiquant de s'initialiser à la date d'aujourd'hui
         * */
        try {
            Date today = new Date();

            this.calendar.setDate(today);

            this.date = DateFormatter.dateToString(today);

            selectedDate.setText(this.date.replace(' ', '/'));
        } catch (OutOfDateRangeException e) {
            Log.e("error", e.getMessage());
        }

        //RECYCLER VIEW
        RecyclerView recyclerView = findViewById(R.id.calendarNotesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager(layoutManager);

        this.noteViewAdapter = new NoteViewAdapter();

        recyclerView.setAdapter(this.noteViewAdapter);

        this.updateNotesList();

        this.updateCalendarHighlightedDays();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.updateNotesList();

        this.updateCalendarHighlightedDays();
    }

    /**
     * Met à jour la liste des notes à afficher
     * */
    private void updateNotesList() {

        this.notes.clear();

        this.notes = this.noteService.getNotesByDate(date);

        this.noteViewAdapter.updateDataSet(notes);


    }
    /**
     * Mise en valeur des jours du calendrier ayant une ou plusieurs notes
     * */
    private void updateCalendarHighlightedDays(){

        List<EventDay> events = new ArrayList<>();

        List<Note> notes = this.noteService.getAllNotes();

        for(Note note : notes){

            Calendar calendarDate = DateFormatter.stringToCalendar(note.getDate());

            EventDay event =  new EventDay(calendarDate, R.drawable.date_event);

            events.add(event);
        }


        this.calendar.setEvents(events);
    }
}
