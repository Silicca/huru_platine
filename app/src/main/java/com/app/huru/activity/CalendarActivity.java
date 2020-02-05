package com.app.huru.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.recyclerview.NoteViewAdapter;
import com.app.huru.model.Note;
import com.app.huru.model.mapper.NoteViewModelMapper;
import com.app.huru.model.view.NoteViewModel;
import com.app.huru.service.NoteService;
import com.app.huru.tools.DateFormatter;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Activité affichant un calendrier et les notes de l'utilisateur
 * */
public class CalendarActivity  extends AppCompatActivity implements ActivityGUI {

    private CalendarView calendar;
    private FloatingActionButton addNoteButton;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NoteViewAdapter noteViewAdapter;

    private NoteService noteService;

    private String date;
    private List<NoteViewModel> notes;

    public static final int ADD_NOTE = 1;

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

        this.addNoteButton = findViewById(R.id.addNoteButton);
        /**
         * Ajout du listener sur le clic du bouton permettant d'ajouter une note au jour sélectionné
         * */
        this.addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddNoteActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        this.calendar = findViewById(R.id.calendar);
        /**
         * Mise en place du listener permettant de récupérer la date actuellement sélectionnée
         * */
        this.calendar.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                date = DateFormatter.eventDayToString(eventDay);
                updateNotesList();

            }
        });
        /**
         * Initialisation du calendrier en lui indiquant de s'initialiser à la date d'aujourd'hui
         * */
        try {
            Date today = new Date();

            this.calendar.setDate(today);

            this.date = DateFormatter.dateToString(today);

        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        //RECYCLER VIEW
        this.recyclerView = findViewById(R.id.calendarNotesRecyclerView);

        this.layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.noteViewAdapter = new NoteViewAdapter();

        this.recyclerView.setAdapter(this.noteViewAdapter);

        /**A SUPPRIMER, JUSTE POUR TESTER*/

        this.updateNotesList();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.updateNotesList();

    }
    /**
     * Met à jour la liste des notes à afficher
     * */
    private void updateNotesList(){

        this.notes.clear();

        for(Note note : this.noteService.getNotesByDate(date)){
            notes.add(NoteViewModelMapper.map(note));
        }

        List<Calendar> calendars = new ArrayList<>();
        calendar.setHighlightedDays(calendars);

        this.noteViewAdapter.updateDataSet(notes);
    }
}
