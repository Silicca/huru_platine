package com.app.huru.activity.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Activity;
import com.app.huru.tools.Animation;

import java.util.ArrayList;
import java.util.List;

public class ActivityViewAdapter extends RecyclerView.Adapter<ActivityViewHolder>{

    private List<Activity> models;

    public ActivityViewAdapter(){
        this.models = new ArrayList<>();
    }
    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View activityView = inflater.inflate(R.layout.activity_view, parent, false);

        return new ActivityViewHolder(activityView);
    }
    /**
     * Met à jour le jeu de données de l'adapteur
     * */
    public void updateDataSet(List<Activity> models){

        this.models.clear();
        this.models.addAll(models);

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {

        holder.updateView(this.models.get(position));
        Animation.setAnimation(holder.getView());
    }

    @Override
    public int getItemCount() {
        return this.models.size();
    }
}
