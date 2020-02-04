package com.app.huru.activity;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.huru.R;

/**
 * Représente l'activité concernant l'ajout d'une note
 * */
public class AddNoteActivity extends AppCompatActivity implements ActivityGUI {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity_layout);
    }

    @Override
    public void setupGUI() {

    }
}
