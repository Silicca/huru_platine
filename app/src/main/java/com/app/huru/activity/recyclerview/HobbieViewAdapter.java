package com.app.huru.activity.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Hobbie;
import com.app.huru.tools.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapteur pour l'affichage des hobbies
 * */
public class HobbieViewAdapter extends RecyclerView.Adapter<HobbieViewHolder> {

    private List<Hobbie> models;

    public HobbieViewAdapter(){
        this.models = new ArrayList<>();
    }

    @NonNull
    @Override
    public HobbieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View hobbieView = inflater.inflate(R.layout.hobbie_view, parent, false);

        return new HobbieViewHolder(hobbieView);
    }

    /**
     * Met à jour le jeu de données de l'adapteur
     * */
    public void updateDataSet(List<Hobbie> models){

        this.models.clear();
        this.models.addAll(models);

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HobbieViewHolder holder, int position) {
        holder.updateView(this.models.get(position));
        Animation.setAnimation(holder.getView());
    }

    @Override
    public int getItemCount() {
        return this.models.size();
    }

}
