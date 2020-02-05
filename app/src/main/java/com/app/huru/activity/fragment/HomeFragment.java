package com.app.huru.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;
import com.app.huru.activity.CalendarActivity;
import com.app.huru.activity.recyclerview.NoteViewAdapter;
import com.app.huru.model.Note;
import com.app.huru.service.NoteService;
import com.app.huru.tools.DateFormatter;
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
    private RecyclerView.LayoutManager layoutManager;
    private NoteViewAdapter noteViewAdapter;
    private FloatingActionButton calendarButtonPlus;

    private NoteService noteService;

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

        this.notes = new ArrayList<>();

        setupGUI();

        return this.parentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setupGUI() {

        //BOUTON
        this.calendarButtonPlus = this.parentView.findViewById(R.id.calendarButtonPlus);

        this.calendarButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        //RECYCLER VIEW
        this.recyclerView = this.parentView.findViewById(R.id.homeNotesRecyclerView);

        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.noteViewAdapter = new NoteViewAdapter();

        this.recyclerView.setAdapter(this.noteViewAdapter);

        this.updateNotesList();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateNotesList();
    }

    /**
     * Met à jour la liste des notes à afficher
     * */
    private void updateNotesList() {

        this.notes.clear();

        this.notes = this.noteService.getNotesByDate(DateFormatter.dateToString(new Date()));

        this.noteViewAdapter.updateDataSet(notes);
    }
}
