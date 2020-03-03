package com.app.huru.activity.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.huru.R;
import com.app.huru.model.Mood;
import com.app.huru.model.Stats;
import com.app.huru.service.StatsService;
import com.app.huru.tools.DateFormatter;
import com.app.huru.tools.DrawableMoodLoader;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class MoodViewHolder extends RecyclerView.ViewHolder {

    private int moodId;

    private TextView mood;
    private ImageView moodImage;

    private View view;

    private StatsService statsService;

    public MoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.view = itemView;

        this.mood = this.view.findViewById(R.id.moodText);

        this.moodImage = this.view.findViewById(R.id.actualMoodImage);

        this.statsService = new StatsService(this.view.getContext());

        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Stats stats = new Stats();
                stats.setMoodId(moodId);
                stats.setDate(DateFormatter.dateToString(new Date()));

                statsService.saveStats(stats);

                Snackbar.make(v, "Humeur mise à jour.", 1000).show();
            }
        });
    }

    /**
     * Met à jour les données du view holder
     * */
    public void updateView(Mood model){

        this.mood.setText(model.getMoodName());
        this.moodId = model.getId();

        this.moodImage.setImageResource(DrawableMoodLoader.load(model));

    }

    public View getView(){
        return this.view;
    }
}
