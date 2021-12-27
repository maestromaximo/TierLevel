package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HighFieldActivity extends AppCompatActivity {

    Intent intent;
    String highFieldName, fieldName;
    TextView highFieldNameTextview, addNameTextview, addDifficultyTextview, addEfficiencyTextview;
    Button backToFieldButton, addLowFieldButton;
    ScrollView scrollView;
    LinearLayout verticalL;
    Button newLfButton;
    Field field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_field);

        intent = getIntent();
        highFieldName = intent.getStringExtra("name");
        fieldName = intent.getStringExtra("field");

        highFieldNameTextview = (TextView) findViewById(R.id.highFieldTextview);
        addNameTextview = (TextView) findViewById(R.id.lowFieldNameTextview);
        addDifficultyTextview = (TextView) findViewById(R.id.lowFieldDifficultyTextview);
        addEfficiencyTextview = (TextView) findViewById(R.id.lowFieldEfficiencyTextview);

        backToFieldButton = (Button) findViewById(R.id.backToFieldButton);
        
        addLowFieldButton = (Button) findViewById(R.id.addLowFieldButton);
        scrollView = (ScrollView) findViewById(R.id.scrollViewHighfield);
        verticalL = (LinearLayout) findViewById(R.id.lowFieldVerticalLayout);

        stablishField();
        highFieldNameTextview.setText(highFieldName);
        backToFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFields();
                finish();
            }
        });//click
        
        addLowFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(addNameTextview.getText());
                double dif = Double.parseDouble(String.valueOf(addDifficultyTextview.getText()));
                int eff = Integer.parseInt(String.valueOf(addEfficiencyTextview.getText()));
                LowField lf;


                if(dif != 0 && eff != 0){
                    lf = new LowField(name, dif, eff);
                }
                else if (dif == 0 && eff != 0){
                    lf = new LowField(name, eff);
                }
                else if (dif != 0 && eff == 0){
                    lf = new LowField(name, dif);
                }
                else if (dif == 0 && eff == 0){
                    lf = new LowField(name);
                }
                else{
                    lf = new LowField();
                    //lf = null;
                }
                field.findHighFieldByName(highFieldName).addLowField(lf);
                saveFields();

                newLfButton = new Button(getApplicationContext());
                newLfButton.setText(lf.getName());
                verticalL.addView(newLfButton);
                newLfButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveFields();
                        switchActivities(highFieldName, lf.getName());
                        //Toast.makeText((Context)HighFieldActivity.this, "clicked" , Toast.LENGTH_LONG).show();
                    }
                });//newHf click
            }
        });//click

    }//onCreate

    private void stablishField() {
        try {
            field = Field.readField(this.getApplicationContext(), fieldName);
            ArrayList<HighField> testy = HighField.readHighField(this.getApplicationContext(), fieldName);
            if (testy != null) {
                field.setHighFields(testy);
            }
        } catch (IOException e) {
            field = null;
        } catch (ClassNotFoundException e) {
            field = null;
        }

        if (field == null){
            field = new Field(fieldName);
            return;
        }

        createButtons();

    }//stablishField

    private void createButtons() {
        for(LowField lf : field.findHighFieldByName(highFieldName).getLowFields()){
            newLfButton = new Button(this);
            newLfButton.setText(lf.getName());
            verticalL.addView(newLfButton);
            newLfButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    saveFields();
                    switchActivities(highFieldName, lf.getName());////////////////////
                    //Toast.makeText((Context)FieldsActivity.this, "clicked" , Toast.LENGTH_LONG).show();
                }
            });//newHf click
        }
        saveFields();
    }//createButtons

    public void saveFields(){
        try {
            Field.uploadField(this.getApplicationContext(), field);
            HighField.uploadHighField(this.getApplicationContext(), field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//saveFields

    private void switchActivities(String Hname, String lowFieldName) {
        Intent switchActivityIntent = new Intent(this, LowFieldActivity.class);
        switchActivityIntent.putExtra("lowfieldName", lowFieldName);
        switchActivityIntent.putExtra("Hname", Hname);
        switchActivityIntent.putExtra("field", field.getFieldName());
        startActivity(switchActivityIntent);
    }//switchActivities

    @Override
    protected void onStop() {
        super.onStop();
        saveFields();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveFields();
    }



//    public void saveFields(){//fewfwefwefwefwe
//
//        Field dummy = new Field(field.getFieldName());
//        try {
//            Field.uploadField(this.getApplicationContext(), dummy);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Field eraser = Field.readField(this.getApplicationContext(), dummy.getFieldName());
//        } catch (IOException e) {
//            dummy = null;
//        } catch (ClassNotFoundException e) {
//            dummy = null;
//        }
//
//        try {
//            Field.uploadField(this.getApplicationContext(), field);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }//saveFieldswerfwefqewfwefew

    //Toast.makeText((Context)MainActivity.this, R.string.message, Toast.LENGTH_LONG).show();
}//HighFieldActivity