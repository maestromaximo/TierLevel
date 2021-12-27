package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    Button profileButton, fieldsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        profileButton = (Button)findViewById(R.id.openProfileButton);
        fieldsButton = (Button)findViewById(R.id.fieldsButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchActivities2();// change it to profile when done

            }
        });//click profile

        fieldsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities1();
            }
        });//click fields
    }//onCreate

    private void switchActivities1() {
        Intent switchActivityIntent = new Intent(this, SelectFieldActivity.class);
        startActivity(switchActivityIntent);
    }//switchActivities

    private void switchActivities2() {
        Intent switchActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(switchActivityIntent);
    }//switchActivities

}//IntroActivity