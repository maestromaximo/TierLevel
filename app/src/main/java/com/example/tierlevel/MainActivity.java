package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button welcomeBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeBackButton = (Button)findViewById(R.id.enterButton);

        welcomeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchActivities();

            }
        });
    }//OnCreate

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, IntroActivity.class);
        startActivity(switchActivityIntent);
    }//switchActivities
}//Main