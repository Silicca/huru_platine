package com.app.huru.activity.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.NoteDetailsActivity;
import com.app.huru.model.Note;

/**
 * Représente un item à afficher dans la liste des notes du jour
 * */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    private int id;
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

        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("note", title.getText().toString());
                Intent intent = new Intent(v.getContext(), NoteDetailsActivity.class);
                intent.putExtra("noteId", id);
                view.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }
    /**
     * Met à jour les données du view holder
     * */
    public void updateView(Note model){

        this.id = model.getId();
        this.title.setText(model.getTitle());
        this.hours.setText(model.getHours());
        this.participants.setText(model.getParticipants());
        this.location.setText(model.getPlace());
    }
    public View getView(){

        return this.view;
    }
}
