package com.app.huru.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;
/**
 * Activité principale de l'application
 * */
public class HomeActivity extends AppCompatActivity implements ActivityGUI{

    private TextView usernameView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.homepage_activity_layout);

        this.setupGUI();
    }

    @Override
    public void setupGUI() {

        this.usernameView = findViewById(R.id.homepage_username);

        String username = getIntent().getExtras().getString("username");

        this.usernameView.setText("Bonjour "+username+" ! Voici un récapitulatif de ta journée.");
    }
}
