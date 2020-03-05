package com.app.huru.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.ActivityGUI;
import com.app.huru.activity.recyclerview.ActivityViewAdapter;
import com.app.huru.model.Activity;
import com.app.huru.service.ActivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment pour la page des proposition d'activités
 * */
public class ActivityFragment extends Fragment implements ActivityGUI {

    private int layout;
    private View parentView;
    private TextView noActivitiesMessage;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ActivityViewAdapter activityViewAdapter;

    private List<Activity> activities;

    private ActivityService activityService;

    public ActivityFragment(){

        super();

        this.layout = R.layout.activity_fragment;

        this.activities = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.layout, container, false);

        this.activityService = new ActivityService(this.parentView.getContext());

        this.setupGUI();

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

        this.noActivitiesMessage = this.parentView.findViewById(R.id.noActivitiesMessage);

        this.recyclerView  = this.parentView.findViewById(R.id.activityRecyclerView);

        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.activityViewAdapter = new ActivityViewAdapter();

        this.recyclerView.setAdapter(this.activityViewAdapter);

        this.updateActivitiesList();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if(menuVisible){

            if(this.activities != null){

                this.updateActivitiesList();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.activities != null){

            this.updateActivitiesList();
        }
    }

    /**
     * Met à jour la liste des activités à proposer
     * */
    private void updateActivitiesList(){

        this.activities.clear();

        this.activities = this.activityService.getActivities();

        if(this.activities.size() == 0){

            this.noActivitiesMessage.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.GONE);

        }else{

            this.noActivitiesMessage.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.VISIBLE);

            this.activityViewAdapter.updateDataSet(this.activities);
        }

    }
}
