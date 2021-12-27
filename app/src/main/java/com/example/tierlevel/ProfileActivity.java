package com.example.tierlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView profileTitleTextview, updateTextview, overallLevelTextview, rankTextview;
    ScrollView scrollView;
    Button goBackButton;
    ArrayList<Field> fields;
    String[] fieldNames = {"Physics","Chemistry","Mathematics","Biology","Computation","Art"};
    Level overall;
    int totalXp = 0;
    //String bigS;

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
    }//onCreate

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
        for (Field field: fields){
            field.updateLv();
            sum += field.getLevel().getCumulativeXp();

        }
        saveFields();
        totalXp = sum;
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