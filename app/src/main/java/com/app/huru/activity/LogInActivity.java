package com.app.huru.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.app.huru.R;
import com.app.huru.service.UserService;

/**
 * Activité de première utilisation de l'application
 * */
public class LogInActivity extends AppCompatActivity implements ActivityGUI{

    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        this.userService = new UserService(getApplicationContext());

        if(this.userService.userAlreadyExists()){

            this.startHomeActivity(this.userService.getUser().getName());
        }
        else{
            this.setupGUI();
        }

    }

    @Override
    public void setupGUI() {


        EditText usernameInput = findViewById(R.id.username_text);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(listener -> {

            String username = usernameInput.getText().toString();

            if(!username.isEmpty()){
                userService.saveUser(username);
                startHomeActivity(username);
            }

        });
    }
    /**
     * Démarrage de l'activité principale
     * */
    private void startHomeActivity(String username){

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("username", username);

        this.getApplicationContext().startActivity(intent);

        finish();
    }
}
