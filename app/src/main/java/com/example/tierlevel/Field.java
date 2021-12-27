package com.example.tierlevel;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable {

    String fieldName;
    Level level;
    ArrayList<HighField> highFields;

    public Field(String fieldName){
        this.fieldName = fieldName;
        this.level = new Level();
        this.highFields = new ArrayList<HighField>();
    }//Constructor

    public Field(String fieldName, Level level){
        this.fieldName = fieldName;
        this.level = level;
        this.highFields = new ArrayList<HighField>();
    }//Constructor

    public Field(String fieldName, ArrayList<HighField> highFields){
        this.fieldName = fieldName;
        this.level = new Level();
        this.highFields = highFields;
    }//Constructor

    public Field(String fieldName, Level level, ArrayList<HighField> highFields){
        this.fieldName = fieldName;
        this.level = level;
        this.highFields = highFields;
    }//Constructor

    public Field(String fieldName, int forcedToLevel){
        this.fieldName = fieldName;
        this.level.levelUpToLevel(forcedToLevel);
        this.highFields = new ArrayList<HighField>();
    }//Constructor

    public Field(String fieldName, int forcedToLevel, ArrayList<HighField> highFields){
        this.fieldName = fieldName;
        this.level.levelUpToLevel(forcedToLevel);
        this.highFields = highFields;
    }//Constructor

    public Level getLevel() {
        return level;
    }

    public void addHighField(HighField hf){
        highFields.add(hf);
    }//addHighfield

    public void updateLv(){
        int xp = 0;

        for(HighField hf: highFields){
            int xpHf = hf.determineXp();
            xp += xpHf;
        }

        level.setCumulativeXp(xp);
        level.updateLv();
    }//updateLv

    public String getFieldName() {
        return fieldName;
    }

    public ArrayList<HighField> getHighFields() {
        return highFields;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setHighFields(ArrayList<HighField> highFields) {
        this.highFields = highFields;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "com.example.tierlevel.Field{" +
                "fieldName='" + fieldName + '\'' +
                ", level=" + level.getCurrentLevel() +
                ", highFields=" + highFields.toString() +
                '}';
    }//toString

    public String listNames(){
        String result = "";
        for (HighField hf:highFields){
            result += hf.getName() + "\n";
        }
        return result;
    }//listNames

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == Field.class) {
            if (((Field) obj).getFieldName() == fieldName && ((Field) obj).level == level) { // same name & level
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }

    }//equals

    public HighField findHighFieldByName(String name){

        for(HighField hf : highFields){

            if(hf.getName().equals(name)){
                return hf;
            }

        }
        return null;
    }//findHighFieldByName

    public Boolean isThereALowField(){

        for (HighField hf: highFields){
            if (hf.getLowFields().size() > 0){
                return true;
            }

        }
        return false;
    }//isThereALowField

    public static void uploadField(Context fileContext, Field field) throws IOException {


        String name = field.getFieldName()+".ser";
        FileOutputStream fileOut = fileContext.openFileOutput(name, Activity.MODE_PRIVATE); // instead of:=> new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        out.reset();//CHANGED MAY NOT WORK, edit: it doesn't.
        out.writeObject(field);


            //out.writeObject(dummy);//dummyTest


        out.close();
        fileOut.close();


    }//uploadField
//    public static void uploadField2(Context fileContext, Field field) throws IOException {
//
//
//        String name = field.getFieldName()+".ser";
//        FileOutputStream fileOut = fileContext.openFileOutput(name, Activity.MODE_PRIVATE); // instead of:=> new FileOutputStream(filename);
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        if(field.isThereALowField() == false) {
//
//            //out.reset();//CHANGED MAY NOT WORK, edit: it doesn't.
//            out.writeObject(field);
//
//
//            //out.writeObject(dummy);//dummyTest
//
//
//            out.close();
//            fileOut.close();
//        }else{
//            FileOutputStream fileut = fileContext.openFileOutput(name, Activity.MODE_PRIVATE);
//            out = new ObjectOutputStream(fileut);
//            Field dummy = new Field("dummy");
//            out.writeObject(dummy);
//            out.close();
//            fileOut.close();
//        }
//
//    }//uploadField

    public static Field readField(Context fileContext, String filename) throws IOException, ClassNotFoundException {
        Field obj = null;
        String name = "/data/data/com.example.tierlevel/files/"+filename+".ser";
        File f = new File(name);
        //System.out.println("TESTTTT");///data/data/com.example.tierlevel/files/Physics.ser
        System.out.println(name);
        if (f.isFile()) {
            //System.out.println("HEKOOOPP");
            //FileInputStream fileIn = fileContext.openFileInput(name);// instead of:=> new FileInputStream(filename);
            FileInputStream fileIn = new FileInputStream (new File(name));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = (Field) in.readObject();
            in.close();
            fileIn.close();
        }
        return obj;
    }//readField




}//Field
