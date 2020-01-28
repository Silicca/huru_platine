package com.app.huru.activity.fragment.home;

import android.content.Context;
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
import com.app.huru.model.view.HomeNoteViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment pour la page principale
 * */
public class HomeFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeNoteViewAdapter homeNoteViewAdapter;

    public HomeFragment(){
        super();

        this.layout = R.layout.home_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

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

        this.recyclerView = this.parentView.findViewById(R.id.homeNotesRecyclerView);

        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.homeNoteViewAdapter = new HomeNoteViewAdapter();

        this.recyclerView.setAdapter(this.homeNoteViewAdapter);

        /**A SUPPRIMER, JUSTE POUR TESTER*/

        List<HomeNoteViewModel> models = new ArrayList<>();
        models.add(new HomeNoteViewModel("Réunion hebdo","10:00","Moi, Jean-yves","Unis, 4 rue entre deux villes"));
        models.add(new HomeNoteViewModel("Faire les courses","15:00","Moi","Auchan Roncq"));
        models.add(new HomeNoteViewModel("Enfants école","17:30","Moi","Collège Albert Calmette"));
        models.add(new HomeNoteViewModel("Préparer à manger","19:30","Moi","A la maison"));

        this.homeNoteViewAdapter.updateDataSet(models);

        /**A SUPPRIMER, JUSTE POUR TESTER*/

    }
}
