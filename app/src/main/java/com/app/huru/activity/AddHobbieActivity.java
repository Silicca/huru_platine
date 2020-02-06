package com.app.huru.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
import com.app.huru.model.Hobbie;
import com.app.huru.service.HobbieService;


/**
 * Représente l'activité concernant l'ajout d'une note
 * */
public class AddHobbieActivity extends AppCompatActivity implements ActivityGUI {

    private Button saveHobbieButton;

    private EditText hobbieName;

    private HobbieService hobbieService;

    private Hobbie hobbie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_hobbie_activity_layout);

        this.hobbieService = new HobbieService(getApplicationContext());

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.saveHobbieButton = findViewById(R.id.saveHobbieButton);
        this.hobbieName = findViewById(R.id.hobbieName);

        this.saveHobbieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hobbieService.saveHobbie(buildHobbie());

                finish();
            }
        });
    }

    private Hobbie buildHobbie(){

        Hobbie hobbie = new Hobbie();

        hobbie.setName(this.hobbieName.getText().toString());

        return hobbie;
    }
}
