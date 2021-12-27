package com.example.tierlevel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tierlevel.databinding.ActivityFieldsBinding;

import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FieldsActivity extends AppCompatActivity {

    Intent intent;
    String fieldName;
    TextView fieldNameTextview, addNameTextview, addDifficultyTextview, addEfficiencyTextview;
    Button backToFieldsButton, addHighFieldButton;
    ScrollView scrollView;
    LinearLayout verticalL;
    Button newHfButton;
    Field field;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        intent = getIntent();
        fieldName = intent.getStringExtra("field");
        fieldNameTextview = (TextView)findViewById(R.id.fieldTitleTextview);
        fieldNameTextview.setText(fieldName);

        addNameTextview = (TextView)findViewById(R.id.highFieldNameTextview);
        addDifficultyTextview = (TextView)findViewById(R.id.difficultyHighFieldTextview);
        addEfficiencyTextview = (TextView)findViewById(R.id.efficiencyHighFieldTextview);

        scrollView = (ScrollView) findViewById(R.id.scrollViewFields);
        verticalL = (LinearLayout) findViewById(R.id.linearLayoutFields);
        //cleanMode();//############################################################################
        stablishField();

        backToFieldsButton = (Button)findViewById(R.id.backToFieldsButton);
        backToFieldsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFields();
                finish();
            }
        });//backToFields Click

        addHighFieldButton = (Button) findViewById(R.id.addHighFieldButton);
        Context context = this;
        addHighFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(addNameTextview.getText());
                double dif = Double.parseDouble(String.valueOf(addDifficultyTextview.getText()));
                int eff = Integer.parseInt(String.valueOf(addEfficiencyTextview.getText()));
                HighField hf;

                if(dif != 0 && eff != 0){
                    hf = new HighField(name, dif, eff);
                }
                else if (dif == 0 && eff != 0){
                    hf = new HighField(name, eff);
                }
                else if (dif != 0 && eff == 0){
                    hf = new HighField(name, dif);
                }
                else if (dif == 0 && eff == 0){
                    hf = new HighField(name);
                }
                else{
                    hf = new HighField();
                    //hf = null;
                }
                field.addHighField(hf);//add
                saveFields();

                newHfButton = new Button(context);
                newHfButton.setText(hf.getName());
                verticalL.addView(newHfButton);
                newHfButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveFields();
                        switchActivities(hf.getName());

                        //Toast.makeText((Context)FieldsActivity.this, "clicked" , Toast.LENGTH_LONG).show();
                    }
                });//newHf click



                //clearInputFields();
            }
        });//add highfield click

    }//onCreate

    /*
    Reads field, if it doesn't exist creates it; if it does, activates button creation
     */
    private void stablishField() {
        try {
            field = Field.readField(this.getApplicationContext(), fieldName);
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

    public void saveFields(){
        try {
            Field.uploadField(this.getApplicationContext(), field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//saveFields

    /*
    Use to manually activate eraser-mode for the files
     */
    private void cleanMode(){
        Field testField = null;
        try {
            Field.uploadField(this.getApplicationContext(), testField);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//cleanMode

    private void createButtons() {
        for(HighField hf : field.getHighFields()){
            newHfButton = new Button(this);
            newHfButton.setText(hf.getName());
            verticalL.addView(newHfButton);
            newHfButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    saveFields();
                    switchActivities(hf.getName());
                    //Toast.makeText((Context)FieldsActivity.this, "clicked" , Toast.LENGTH_LONG).show();
                }
            });//newHf click
        }
        saveFields();
    }//createButtons

    private void switchActivities(String name) {
        Intent switchActivityIntent = new Intent(this, HighFieldActivity.class);
        switchActivityIntent.putExtra("name", name);
        switchActivityIntent.putExtra("field", field.getFieldName());
        startActivity(switchActivityIntent);
    }//switchActivities

    public void clearInputFields(){
        addEfficiencyTextview.setText(0);
        addDifficultyTextview.setText(0);
        addNameTextview.setText("");
    }

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









}//FieldsActivity

