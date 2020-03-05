package com.app.huru.activity.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.activity.HobbieDetailsActivity;
import com.app.huru.model.Hobbie;

/**
 * Représente un item à afficher dans la liste des hobbies
 * */
public class HobbieViewHolder extends RecyclerView.ViewHolder {

    private int id;
    private TextView hobbieNameText;

    private View view;

    public HobbieViewHolder(@NonNull View itemView) {
        super(itemView);

        this.view = itemView;

        this.hobbieNameText = itemView.findViewById(R.id.hobbieNameText);

        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), HobbieDetailsActivity.class);
                intent.putExtra("hobbieId", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    /**
     * Met à jour les données du view holder
     * */
    public void updateView(Hobbie model){

        this.id = model.getId();
        this.hobbieNameText.setText(model.getName());
    }

    public View getView(){

        return this.view;
    }
}
