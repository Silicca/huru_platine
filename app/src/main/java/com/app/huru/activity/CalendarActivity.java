package com.app.huru.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.recyclerview.NoteViewAdapter;
import com.app.huru.model.view.NoteViewModel;
import com.app.huru.service.NoteService;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Activité affichant un calendrier et les notes de l'utilisateur
 * */
public class CalendarActivity  extends AppCompatActivity implements ActivityGUI {

    private CalendarView calendar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NoteViewAdapter noteViewAdapter;

    private NoteService noteService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_layout);

        this.noteService = new NoteService(this.getApplicationContext());

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.calendar = findViewById(R.id.calendar);

        try {
            this.calendar.setDate(new Date());
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

        List<NoteViewModel> models = new ArrayList<>();
        models.add(new NoteViewModel("Réunion hebdo","10:00","Moi, Jean-yves","Unis, 4 rue entre deux villes"));
        models.add(new NoteViewModel("Faire les courses","15:00","Moi","Auchan Roncq"));
        models.add(new NoteViewModel("Enfants école","17:30","Moi","Collège Albert Calmette"));
        models.add(new NoteViewModel("Préparer à manger","19:30","Moi","A la maison"));

        List<Calendar> calendars = new ArrayList<>();
        calendar.setHighlightedDays(calendars);

        this.noteViewAdapter.updateDataSet(models);
    }
}
