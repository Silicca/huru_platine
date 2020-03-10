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
import com.app.huru.model.Hobbie;
import com.app.huru.model.Mood;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobbie_details_activity_layout);

        this.hobbieService = new HobbieService(getApplicationContext());
        this.moodService = new MoodService(getApplicationContext());

        int id = getIntent().getExtras().getInt("hobbieId");

        this.hobbie = this.hobbieService.getHobbie(id);

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
        this.selectMoodText = findViewById(R.id.selectMoodText);

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
        this.hobbieService.updateHobbie(this.hobbie);

        finish();
    }
    /**
     * Suppression du hobbie actuel
     * */
    private void removeHobbie(){

        this.hobbieService.removeHobbie(this.hobbie.getId());

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
}
