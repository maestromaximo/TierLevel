package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SelectFieldActivity extends AppCompatActivity {

    Button goBackButton, addFieldButton, mathButton, phyButton, chemButton, bioButton, artButton, csButton;

    ArrayList<Field> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_field);

        goBackButton = (Button)findViewById(R.id.goBackButton1);
        addFieldButton = (Button)findViewById(R.id.addFieldButton);
        mathButton = (Button)findViewById(R.id.mathematicsFieldButton);
        phyButton = (Button)findViewById(R.id.physicsFieldButton);
        chemButton = (Button)findViewById(R.id.chemestryFieldButton);
        bioButton = (Button)findViewById(R.id.biologyFieldButton);
        artButton = (Button)findViewById(R.id.artFieldButton);
        csButton = (Button)findViewById(R.id.computationFieldButton);

        fields = new ArrayList<Field>();
        Field math = new Field("mathematics");
        fields.add(math);
        Field phy = new Field("physics");
        fields.add(phy);
        Field chem = new Field("chemistry");
        fields.add(chem);
        Field bio = new Field("biology");
        fields.add(bio);
        Field art = new Field("art");
        fields.add(art);
        Field cs = new Field("computation");
        fields.add(cs);

        addFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });//addField click

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//goBack click

        mathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Mathematics");

            }
        });//math

        phyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Physics");
            }
        });//phy

        chemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Chemistry");

            }
        });//chem

        bioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Biology");
            }
        });//bio

        artButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Art");
            }
        });//art

        csButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities("Computation");

            }
        });//cs


    }//onCreate



    private void switchActivities(String name) {
        Intent switchActivityIntent = new Intent(this, FieldsActivity.class);
        switchActivityIntent.putExtra("field", name);
        startActivity(switchActivityIntent);
    }//switchActivities

}//SelectFiledActivity