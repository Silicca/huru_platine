package com.app.huru.activity.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.view.NoteViewModel;
import com.app.huru.tools.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapteur pour l'affichage des notes
 * */
public class NoteViewAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteViewModel> models;

    public NoteViewAdapter(){
        this.models = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View noteView = inflater.inflate(R.layout.home_note_view, parent, false);

        return new NoteViewHolder(noteView);
    }

    /**
     * Met à jour le jeu de données de l'adapteur
     * */
    public void updateDataSet(List<NoteViewModel> models){

        this.models.clear();
        this.models.addAll(models);

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.updateView(this.models.get(position));
        Animation.setAnimation(holder.getView());
    }

    @Override
    public int getItemCount() {
        return this.models.size();
    }

}
