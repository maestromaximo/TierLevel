package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class IntroActivity extends AppCompatActivity {
    Button profileButton, fieldsButton, clearProgressButton;
    TextView clearPTextview;
    String[] fieldNames = {"Physics","Chemistry","Mathematics","Biology","Computation","Art",
                            "Physics1","Chemistry1","Mathematics1","Biology1","Computation1","Art1"};
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        count = 0;

        profileButton = (Button)findViewById(R.id.openProfileButton);
        fieldsButton = (Button)findViewById(R.id.fieldsButton);
        clearPTextview = (TextView)findViewById(R.id.clearProgressTextview);
        clearProgressButton = (Button)findViewById(R.id.clearProgessButton);

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
        clearPTextview.setText("Hit 3 times to clear.");
        clearProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;

                if(count == 3){
                    deleteFiles();
                    clearPTextview.setText("deleted");

                }
                else if(count < 3){
                    clearPTextview.setText("press "+ String.valueOf((3 - count)) + " times to clear.");
                }
            }
        });//click

    }//onCreate

    private void deleteFiles() {

        for (String filename : fieldNames) {

            String name = "/data/data/com.example.tierlevel/files/" + filename + ".ser";
            File f = new File(name);

            if (f.isFile()) {
                f.delete();
            }//if
        }//for

    }

    private void switchActivities1() {
        Intent switchActivityIntent = new Intent(this, SelectFieldActivity.class);
        startActivity(switchActivityIntent);
    }//switchActivities

    private void switchActivities2() {
        Intent switchActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(switchActivityIntent);
    }//switchActivities

}//IntroActivity