package com.app.huru.activity.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.view.NoteViewModel;

/**
 * Représente un item à afficher dans la liste des notes du jour
 * */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView hours;
    private TextView participants;
    private TextView location;

    private View view;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        this.view = itemView;

        this.title = itemView.findViewById(R.id.noteTitleText);
        this.hours = itemView.findViewById(R.id.noteHoursText);
        this.participants = itemView.findViewById(R.id.noteParticipantsText);
        this.location = itemView.findViewById(R.id.noteLocationText);
    }
    /**
     * Met à jour les données du view holder
     * */
    public void updateView(NoteViewModel model){

        this.title.setText(model.getTitle());
        this.hours.setText(model.getHours());
        this.participants.setText(model.getParticipants());
        this.location.setText(model.getLocation());
    }
    public View getView(){

        return this.view;
    }
}
