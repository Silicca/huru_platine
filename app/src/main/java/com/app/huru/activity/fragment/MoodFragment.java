package com.app.huru.activity.fragment;


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
import com.app.huru.activity.recyclerview.MoodViewAdapter;
import com.app.huru.model.Mood;
import com.app.huru.service.MoodService;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment concernant les humeurs
 * */
public class MoodFragment extends Fragment implements ActivityGUI {

    private int layout;

    private View parentView;

    private MoodViewAdapter moodViewAdapter;

    private MoodService moodService;

    private List<Mood> moods;

    public MoodFragment(){
        super();
        this.layout = R.layout.mood_fragment;
        this.moods = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        this.moodService = new MoodService(this.parentView.getContext());

        this.setupGUI();

        return this.parentView;
    }
    @Override
    public void setupGUI() {

        RecyclerView recyclerView = this.parentView.findViewById(R.id.moodsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager(layoutManager);

        this.moodViewAdapter = new MoodViewAdapter();

        recyclerView.setAdapter(this.moodViewAdapter);

        this.moodViewAdapter.updateDataSet(this.moods);

        this.updateMoodsList();
    }


    /**
     * Mise à jour de la liste des humeurs
     * */
    private void updateMoodsList(){

        this.moods.clear();
        this.moods = this.moodService.getAllMoods();
        this.moodViewAdapter.updateDataSet(this.moods);
    }
}
