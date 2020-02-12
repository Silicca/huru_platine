package com.app.huru.activity.recyclerview;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Mood;

public class MoodViewHolder extends RecyclerView.ViewHolder {

    private TextView mood;
    private ImageView moodImage;

    private View view;

    public MoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.view = itemView;

        this.mood = this.view.findViewById(R.id.moodText);

        this.moodImage = this.view.findViewById(R.id.moodImage);
    }

    /**
     * Met à jour les données du view holder
     * */
    public void updateView(Mood model){

        this.mood.setText(model.getMoodName());

        int moodImageDrawable = 0;

        switch (model.getMoodName()){
            case "Content(e)":
                moodImageDrawable = R.drawable.content;
            break;
            case "Triste":
                moodImageDrawable = R.drawable.triste;
            break;
            case "Fatigué(e)":
                moodImageDrawable = R.drawable.fatigue;
            break;
            case "Stressé(e)":
                moodImageDrawable = R.drawable.stress;
            break;
            case "En colère":
                moodImageDrawable = R.drawable.colere;
            break;
        }
        this.moodImage.setImageResource(moodImageDrawable);

    }

    public View getView(){
        return this.view;
    }
}
