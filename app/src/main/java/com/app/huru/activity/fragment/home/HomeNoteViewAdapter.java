package com.app.huru.activity.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.view.HomeNoteViewModel;
import com.app.huru.tools.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapteur pour l'affichage des notes
 * */
public class HomeNoteViewAdapter extends RecyclerView.Adapter<HomeNoteViewHolder> {

    private List<HomeNoteViewModel> models;

    public HomeNoteViewAdapter(){
        this.models = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View noteView = inflater.inflate(R.layout.home_note_view, parent, false);

        return new HomeNoteViewHolder(noteView);
    }

    /**
     * Met à jour le jeu de données de l'adapteur
     * */
    public void updateDataSet(List<HomeNoteViewModel> models){

        this.models.clear();
        this.models.addAll(models);

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeNoteViewHolder holder, int position) {
        holder.updateView(this.models.get(position));
        Animation.setAnimation(holder.getView());
    }

    @Override
    public int getItemCount() {
        return this.models.size();
    }

}
