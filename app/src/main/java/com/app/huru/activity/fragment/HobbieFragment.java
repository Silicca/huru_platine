package com.app.huru.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;
import com.app.huru.activity.AddHobbieActivity;
import com.app.huru.activity.recyclerview.HobbieViewAdapter;
import com.app.huru.model.Hobbie;
import com.app.huru.service.HobbieService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment pour la page principale
 * */
public class HobbieFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;

    private HobbieViewAdapter hobbieViewAdapter;

    private HobbieService hobbieService;

    private List<Hobbie> hobbies;

    public HobbieFragment(){
        super();

        this.layout = R.layout.hobbie_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        this.hobbieService = new HobbieService(this.parentView.getContext());

        this.hobbies = new ArrayList<>();

        setupGUI();

        return this.parentView;
    }

    @Override
    public void setupGUI() {

        FloatingActionButton hobbieButtonPlus = this.parentView.findViewById(R.id.hobbiesButtonPlus);

        hobbieButtonPlus.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), AddHobbieActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);

        });


        RecyclerView recyclerView = this.parentView.findViewById(R.id.hobbiesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager(layoutManager);

        this.hobbieViewAdapter = new HobbieViewAdapter();

        recyclerView.setAdapter(this.hobbieViewAdapter);

        this.updateHobbiesList();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateHobbiesList();
    }

    /**
     * Met à jour la liste des hobbies de l'utilisateur
     * */
    private void updateHobbiesList() {

        this.hobbies.clear();

        this.hobbies = this.hobbieService.getAllHobbies();

        this.hobbieViewAdapter.updateDataSet(hobbies);
    }
}
