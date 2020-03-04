package com.app.huru.activity.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Activity;
/**
 * Représente un item à afficher dans la liste des activités
 * */
public class ActivityViewHolder extends RecyclerView.ViewHolder  {

    private int id;
    private TextView activityNameText;

    private View view;

    public ActivityViewHolder(@NonNull View itemView) {

        super(itemView);
        this.view = itemView;
        this.activityNameText = itemView.findViewById(R.id.activityNameText);
    }

    /**
     * Met à jour les données du view holder
     * */
    public void updateView(Activity activity){

        this.id = activity.getId();
        this.activityNameText.setText(activity.getActivityName());
    }

    public View getView(){

        return this.view;
    }
}
