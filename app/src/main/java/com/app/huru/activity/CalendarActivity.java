package com.app.huru.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;

/**
 * Activité affichant un calendrier et les notes de l'utilisateur
 * */
public class CalendarActivity  extends AppCompatActivity implements ActivityGUI {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_layout);
    }

    @Override
    public void setupGUI() {

    }
}
