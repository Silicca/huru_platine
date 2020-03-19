package com.app.huru.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;
import com.app.huru.activity.CalendarActivity;
import com.app.huru.activity.recyclerview.NoteViewAdapter;
import com.app.huru.model.Mood;
import com.app.huru.model.Note;
import com.app.huru.model.Stats;
import com.app.huru.service.NoteService;
import com.app.huru.service.StatsService;
import com.app.huru.tools.DateFormatter;
import com.app.huru.tools.DrawableMoodLoader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fragment pour la page principale
 * */
public class HomeFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;

    private RecyclerView recyclerView;
    private NoteViewAdapter noteViewAdapter;

    private TextView actualMoodText;
    private TextView noNoteMessage;
    private ImageView actualMoodImage;

    private NoteService noteService;
    private StatsService statsService;


    private List<Note> notes;

    public HomeFragment(){
        super();

        this.layout = R.layout.home_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        this.noteService = new NoteService(this.parentView.getContext());
        this.statsService = new StatsService(this.parentView.getContext());

        this.notes = new ArrayList<>();

        setupGUI();

        return this.parentView;
    }

    @Override
    public void setupGUI() {

        this.actualMoodText = this.parentView.findViewById(R.id.actualMoodText);
        this.actualMoodImage = this.parentView.findViewById(R.id.actualMoodImage);
        this.noNoteMessage = this.parentView.findViewById(R.id.noNotesMessage);
        this.noNoteMessage.setVisibility(View.GONE);


        FloatingActionButton calendarButtonPlus = this.parentView.findViewById(R.id.calendarButtonPlus);

        calendarButtonPlus.setOnClickListener(view ->{

            Intent intent = new Intent(view.getContext(), CalendarActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);

        });

        //RECYCLER VIEW
        this.recyclerView = this.parentView.findViewById(R.id.homeNotesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(layoutManager);

        this.noteViewAdapter = new NoteViewAdapter();

        this.recyclerView.setAdapter(this.noteViewAdapter);

        this.updateNotesList();

        this.updateActualMood();

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if(menuVisible){

            if(this.notes != null){

                this.updateNotesList();
                this.updateActualMood();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.notes != null){

            this.updateNotesList();
            this.updateActualMood();
        }
    }

    /**
     * Met à jour la liste des notes à afficher
     * */
    private void updateNotesList() {

        this.notes.clear();

        this.notes = this.noteService.getNotesByDate(DateFormatter.dateToString(new Date()));

        if(this.notes.isEmpty()){

            this.recyclerView.setVisibility(View.GONE);
            this.noNoteMessage.setVisibility(View.VISIBLE);

        }else{

            this.recyclerView.setVisibility(View.VISIBLE);
            this.noNoteMessage.setVisibility(View.GONE);
            this.noteViewAdapter.updateDataSet(notes);
        }

        this.noteViewAdapter.updateDataSet(notes);

    }

    private void updateActualMood(){

        Mood actualMood;

        List<Stats> lastStats = this.statsService.getAllStats();

        if(!lastStats.isEmpty()){

            actualMood = lastStats.get(lastStats.size()-1).getMood();

            actualMoodImage.setImageResource(DrawableMoodLoader.load(actualMood));
            actualMoodText.setText(actualMood.getMoodName());
        }else{
            actualMoodImage.setVisibility(View.GONE);
            actualMoodText.setText("Non précisé");
        }

    }
}
