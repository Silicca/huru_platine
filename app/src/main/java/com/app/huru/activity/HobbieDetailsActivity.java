package com.app.huru.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
import com.app.huru.model.Hobbie;
import com.app.huru.service.HobbieService;

public class HobbieDetailsActivity extends AppCompatActivity implements ActivityGUI {

    private HobbieService hobbieService;

    private Button modifyNoteButton;
    private Button saveNoteButton;

    private TextView hobbieName;

    private Hobbie hobbie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobbie_details_activity_layout);

        this.hobbieService = new HobbieService(getApplicationContext());
        int id = getIntent().getExtras().getInt("hobbieId");
        this.hobbie = this.hobbieService.getHobbie(id);
        this.setupGUI();
    }

    @Override
    public void setupGUI() {

    }
}
