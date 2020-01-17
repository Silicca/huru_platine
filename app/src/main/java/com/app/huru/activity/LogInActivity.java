package com.app.huru.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.huru.R;
/**
 * Activité de première utilisation de l'application
 * */
public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
    }
}
