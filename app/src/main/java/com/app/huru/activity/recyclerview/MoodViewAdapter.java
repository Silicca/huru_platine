package com.app.huru.activity.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Mood;
import com.app.huru.tools.Animation;

import java.util.ArrayList;
import java.util.List;

public class MoodViewAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private List<Mood> models;

    public MoodViewAdapter(){
        this.models = new ArrayList<>();
    }
    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View moodView = inflater.inflate(R.layout.mood_view, parent, false);

        return new MoodViewHolder(moodView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        holder.updateView(this.models.get(position));
        Animation.setAnimation(holder.getView());
    }
    /**
     * Met à jour le jeu de données de l'adapteur
     * */
    public void updateDataSet(List<Mood> models){

        this.models.clear();
        this.models.addAll(models);

        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return this.models.size();
    }
}
