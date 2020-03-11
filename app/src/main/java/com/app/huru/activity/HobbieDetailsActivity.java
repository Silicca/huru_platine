package com.app.huru.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
import com.app.huru.model.Activity;
import com.app.huru.model.Hobbie;
import com.app.huru.model.Mood;
import com.app.huru.service.ActivityService;
import com.app.huru.service.HobbieService;
import com.app.huru.service.MoodService;
import com.app.huru.tools.DrawableMoodLoader;

import java.util.List;

/**
 * Activité affichant les détails d'un hobbie
 * */
public class HobbieDetailsActivity extends AppCompatActivity implements ActivityGUI {

    private HobbieService hobbieService;
    private MoodService moodService;
    private ActivityService activityService;

    private Button modifyHobbieButton;
    private Button removeHobbieButton;
    private Button shiftLeftButton;
    private Button shiftRightButton;
    private ImageView selectMoodImage;
    private TextView selectMoodText;

    private TextView hobbieName;

    private Hobbie hobbie;

    private int selectMoodIndex;
    private List<Mood> moods;
    private String oldHobbieName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobbie_details_activity_layout);

        this.hobbieService = new HobbieService(getApplicationContext());
        this.moodService = new MoodService(getApplicationContext());
        this.activityService = new ActivityService(getApplicationContext());

        int id = getIntent().getExtras().getInt("hobbieId");

        this.hobbie = this.hobbieService.getHobbie(id);

        this.oldHobbieName = this.hobbie.getName();

        this.moods = this.moodService.getAllMoods();

        this.selectMoodIndex = 0;

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.modifyHobbieButton = findViewById(R.id.modifyHobbieButton);
        this.removeHobbieButton = findViewById(R.id.removeHobbieButton);
        this.shiftLeftButton = findViewById(R.id.shiftLeftButton);
        this.shiftRightButton = findViewById(R.id.shiftRightButton);

        this.selectMoodImage = findViewById(R.id.selectMoodImage);
        this.selectMoodImage.setImageResource(DrawableMoodLoader.load(this.hobbie.getMood()));

        this.selectMoodText = findViewById(R.id.selectMoodText);
        this.selectMoodText.setText(this.hobbie.getMood().getMoodName());

        this.hobbieName = findViewById(R.id.hobbieName);
        this.hobbieName.setText(this.hobbie.getName());

        this.modifyHobbieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveModification();
            }
        });

        this.removeHobbieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeHobbie();
            }
        });

        this.shiftLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectMoodIndex --;
                selectMood();
            }
        });

        this.shiftRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectMoodIndex ++;
                selectMood();
            }
        });
    }

    /**
     * Enregistrement des modifications du hobbie actuel
     * */
    private void saveModification(){

        this.hobbie.setName(this.hobbieName.getText().toString());
        this.hobbie.setMood(this.moods.get(this.selectMoodIndex));
        this.hobbieService.updateHobbie(this.hobbie);

        updateActivity(this.hobbie);

        finish();
    }
    /**
     * Suppression du hobbie actuel
     * */
    private void removeHobbie(){

        this.hobbieService.removeHobbie(this.hobbie.getId());
        this.activityService.removeActivityByName(this.oldHobbieName);

        finish();
    }

    private void selectMood(){

        if(this.selectMoodIndex < 0){
            this.selectMoodIndex = this.moods.size()-1;
        }

        if(this.selectMoodIndex == this.moods.size()){
            this.selectMoodIndex = 0;
        }

        this.selectMoodImage.setImageResource(DrawableMoodLoader.load(this.moods.get(this.selectMoodIndex)));
        this.selectMoodText.setText(this.moods.get(this.selectMoodIndex).getMoodName());
    }

    private void updateActivity(Hobbie hobbie){

        this.activityService.removeActivityByName(oldHobbieName);

        Activity activity = new Activity();

        activity.setActivityName(hobbie.getName());

        switch (hobbie.getMood().getMoodName()){
            case "Content(e)":
                activity.setPercentJoiceMin(50);
                activity.setPercentJoiceMax(100);
                break;
            case "Triste":
                activity.setPercentSadMin(50);
                activity.setPercentSadMax(100);
                break;
            case "En colère":
                activity.setPercentAngryMin(50);
                activity.setPercentAngryMax(100);
                break;
            case "Stressé(e)":
                activity.setPercentStressMin(50);
                activity.setPercentStressMax(100);
                break;
            case "Fatigué(e)":
                activity.setPercentTiredMin(50);
                activity.setPercentTiredMax(100);
                break;
        }

        this.activityService.saveActivity(activity);
    }
}
