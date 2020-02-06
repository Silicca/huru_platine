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
import com.app.huru.activity.AddHobbieActivity;
import com.app.huru.activity.HobbieDetailsActivity;
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

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HobbieViewAdapter hobbieViewAdapter;
    private FloatingActionButton hobbieButtonPlus;

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
        this.hobbieButtonPlus = this.parentView.findViewById(R.id.hobbiesButtonPlus);

        this.hobbieButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AddHobbieActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        //RECYCLER VIEW
        this.recyclerView = this.parentView.findViewById(R.id.hobbiesRecyclerView);

        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.hobbieViewAdapter = new HobbieViewAdapter();

        this.recyclerView.setAdapter(this.hobbieViewAdapter);

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
