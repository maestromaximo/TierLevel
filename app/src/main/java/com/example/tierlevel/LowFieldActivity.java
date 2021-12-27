package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class LowFieldActivity extends AppCompatActivity {

    TextView diplayLowFieldTextview, lowFieldTextview;
    Button isItCompleteButton, backToHfButton;
    Intent intent;
    String lowFieldName, highFieldName, fieldName;
    Field field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_field);
        intent = getIntent();
        diplayLowFieldTextview = (TextView) findViewById(R.id.displayLowFieldTextview);
        lowFieldTextview = (TextView) findViewById(R.id.lowFieldTextview);
        isItCompleteButton = (Button) findViewById(R.id.isItCompleteButton);
        backToHfButton = (Button)findViewById(R.id.backToHfButton);
        lowFieldName = intent.getStringExtra("lowfieldName");
        highFieldName = intent.getStringExtra("Hname");
        fieldName = intent.getStringExtra("field");

        lowFieldTextview.setText(lowFieldName);

        stablishField();
        backToHfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFields();
                finish();
            }
        });//click

        isItCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isIt = field.findHighFieldByName(highFieldName).findLowFieldByName(lowFieldName).getIsComplete();
                field.findHighFieldByName(highFieldName).findLowFieldByName(lowFieldName).setIsComplete(!isIt);
                saveFields();
                updateText();
            }
        });

        updateText();


    }//onCreate

    private void updateText(){
        diplayLowFieldTextview.setText(field.findHighFieldByName(highFieldName).findLowFieldByName(lowFieldName).toString());
    }


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


    }//stablishField

    public void saveFields(){
        try {
            Field.uploadField(this.getApplicationContext(), field);
            HighField.uploadHighField(this.getApplicationContext(), field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//saveFields

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

}//LowFieldActivity