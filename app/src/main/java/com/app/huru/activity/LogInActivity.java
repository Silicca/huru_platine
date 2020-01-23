package com.app.huru.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.huru.R;
import com.app.huru.service.UserService;

/**
 * Activité de première utilisation de l'application
 * */
public class LogInActivity extends AppCompatActivity {

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        this.userService = new UserService(this.getApplicationContext());
    }
}
