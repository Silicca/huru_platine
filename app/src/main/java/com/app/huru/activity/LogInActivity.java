package com.app.huru.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.huru.R;
import com.app.huru.service.UserService;

/**
 * Activité de première utilisation de l'application
 * */
public class LogInActivity extends AppCompatActivity implements ActivityGUI{

    private UserService userService;

    private Button loginButton;

    private EditText userNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        this.userService = new UserService(this.getApplicationContext());

        if(this.userService.userAlreadyExists()){

            Log.v("user", this.userService.getUser().toString());
        }
        else{
            this.setupGUI();
        }

    }

    @Override
    public void setupGUI() {

        this.userNameInput = findViewById(R.id.username);

        this.loginButton = findViewById(R.id.loginButton);

        this.loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v("user", userNameInput.getText().toString());
            }
        });


    }
}
