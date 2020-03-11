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
 * Représente l'activité concernant l'ajout d'une note
 * */
public class AddHobbieActivity extends AppCompatActivity implements ActivityGUI {

    private Button saveHobbieButton;
    private Button shiftLeftButtonAdd;
    private Button shiftRightButtonAdd;
    private ImageView selectMoodImageAdd;
    private TextView selectMoodTextAdd;

    private EditText hobbieName;

    private HobbieService hobbieService;
    private MoodService moodService;
    private ActivityService activityService;

    private Hobbie hobbie;

    private int selectMoodIndex;
    private List<Mood> moods;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_hobbie_activity_layout);

        this.hobbieService = new HobbieService(getApplicationContext());
        this.moodService = new MoodService((getApplicationContext()));
        this.activityService = new ActivityService(getApplicationContext());

        this.moods = this.moodService.getAllMoods();
        this.selectMoodIndex = 0;

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.saveHobbieButton = findViewById(R.id.saveHobbieButton);
        this.hobbieName = findViewById(R.id.hobbieName);
        this.shiftLeftButtonAdd = findViewById(R.id.shiftLeftButtonAdd);
        this.shiftRightButtonAdd = findViewById(R.id.shiftRightButtonAdd);

        this.selectMoodImageAdd = findViewById(R.id.selectMoodImageAdd);
        this.selectMoodImageAdd.setImageResource(DrawableMoodLoader.load(this.moods.get(this.selectMoodIndex)));

        this.selectMoodTextAdd = findViewById(R.id.selectMoodTextAdd);
        this.selectMoodTextAdd.setText(this.moods.get(this.selectMoodIndex).getMoodName());

        this.saveHobbieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hobbie hobbie = buildHobbie();
                hobbieService.saveHobbie(hobbie);
                saveActivity(hobbie);

                finish();
            }
        });

        this.shiftLeftButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectMoodIndex --;
                selectMood();
            }
        });

        this.shiftRightButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectMoodIndex ++;
                selectMood();
            }
        });
    }

    /**
     * Construction d'un nouvel objet Hobbie
     * @see Hobbie
     * */
    private Hobbie buildHobbie(){

        Hobbie hobbie = new Hobbie();

        hobbie.setName(this.hobbieName.getText().toString());
        hobbie.setMood(this.moods.get(this.selectMoodIndex));
        return hobbie;
    }

    private void saveActivity(Hobbie hobbie){

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

    private void selectMood(){

        if(this.selectMoodIndex < 0){
            this.selectMoodIndex = this.moods.size()-1;
        }

        if(this.selectMoodIndex == this.moods.size()){
            this.selectMoodIndex = 0;
        }

        this.selectMoodImageAdd.setImageResource(DrawableMoodLoader.load(this.moods.get(this.selectMoodIndex)));
        this.selectMoodTextAdd.setText(this.moods.get(this.selectMoodIndex).getMoodName());
    }
}
