package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class ProfileActivity extends AppCompatActivity {

    TextView profileTitleTextview, updateTextview, overallLevelTextview, rankTextview;
    TextView studyTimeTextview;
    ScrollView scrollView;
    Button goBackButton, studyButton;
    ArrayList<Field> fields;
    String[] fieldNames = {"Physics","Chemistry","Mathematics","Biology","Computation","Art"};
    Level overall;
    int totalXp = 0, studyXp;
    //String bigS;
    long tStart, tEnd, tDelta;
    Boolean isItStudy = false;

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = preferences.edit();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        goBackButton = (Button) findViewById(R.id.backToIntroFromProfileButton);
        scrollView = (ScrollView) findViewById(R.id.profileScrollView);
        profileTitleTextview = (TextView) findViewById(R.id.profileTitleTextview);
        updateTextview = (TextView) findViewById(R.id.insertUpdateProfileTextview);
        overallLevelTextview = (TextView) findViewById(R.id.overallLevelTextview);
        rankTextview = (TextView) findViewById(R.id.rankTextview);
        studyButton = (Button)findViewById(R.id.studyTimeButton);
        studyTimeTextview = (TextView) findViewById(R.id.studyTimeTextview);
        fields = new ArrayList<Field>();

        makeFields();
        calculateTotalXp();
        overall = new Level(totalXp);
        overallLevelTextview.setText("Current Level: "+String.valueOf(overall.getCurrentLevel()));
        updateCareer();
        determineRank();
        
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFields();
                finish();
            }
        });

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateStudyTime();
            }
        });//click
    }//onCreate

    private void activateStudyTime() {


        saveFields();
        try {
            isItStudy = preferences.getBoolean("isItCliked", false);
        }catch (Exception e){
            studyTimeTextview.setText("//error// Try again");
        }
        if (!isItStudy) {
            tStart = System.currentTimeMillis();
            editor.putLong("time", tStart);
            isItStudy = true;
            editor.putBoolean("isItCliked", isItStudy);
            editor.apply();
            studyTimeTextview.setText("Started!");
        }else{

            tStart = preferences.getLong("time", 0);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;
            isItStudy = false;
            editor.putBoolean("isItCliked", isItStudy);
            editor.apply();
            int xpEarned = xpEarned(tDelta);
            studyTimeTextview.setText("Xp Earned: "+ String.valueOf(xpEarned)+"!");

            int xpxp = preferences.getInt("xpxp", 0) + xpEarned;
            editor.putInt("xpxp", xpxp);
            editor.apply();

        }

        saveFields();
    }//activateStudyTime

    /*
    Half the quantity of minutes elapsed
     */
    private int xpEarned(long tDelta) {

        int timeInMinutes = ((int)(tDelta/1000)/60)/2;
        return timeInMinutes;

    }

    private void determineRank() {

        rankTextview.setText("Current Rank: "+overall.convertToRank());

    }

    private void updateCareer() {
        overall.updateLv();
        String res = "Current Xp: "+overall.getRemainingXp()+"\n"
                      +"Xp to next Lv: "+overall.getXpToNextLevel()+"\n"+"\n";

        for (Field field : fields){
            res += field.getFieldName()+":\n"+"Field level: "+ String.valueOf(field.getLevel().getCurrentLevel())+
                    "\n"+ "Rank: "+ field.getLevel().convertToRank() + "\n"+ "Number of Highfields: "+
                    String.valueOf(field.highFields.size()) + "\n\n";
        }

        updateTextview.setText(res);

    }

    private void calculateTotalXp() {
        int sum = 0;
        try {
            studyXp = preferences.getInt("xpxp", 0);
        }catch (Exception e){
            studyXp = 0;
        }
        for (Field field: fields){
            field.updateLv();
            sum += field.getLevel().getCumulativeXp();

        }
        saveFields();
        totalXp = sum + studyXp;
    }

    private void makeFields() {

        for(String name: fieldNames){

            Field field = stablishField(name);
            fields.add(field);
        }


    }

    private Field stablishField(String fieldName) {
        Field field;
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
            return field = new Field(fieldName);

        }
        return field;


    }//stablishField

    public void saveFields(){
        for(Field field: fields) {
            try {
                Field.uploadField(this.getApplicationContext(), field);
                HighField.uploadHighField(this.getApplicationContext(), field);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}//ProfileActivity