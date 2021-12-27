package com.example.tierlevel;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
Not as abstract as a general field, but non objectable enough to encompass a wide variety of subjects,
for example: Calculus may be a HighFieldActivity, but limits is a com.example.tierlevel.LowField of calculus.
 */
public class HighField implements Serializable {
    String name;
    double difficulty; // 1 - 10
    int efficiency; // 1-3 int
    ArrayList<LowField> lowFields;
    int xp = 0;

//without lowfields
    public HighField(){
        this.name = "unnamed";
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.lowFields = new ArrayList<LowField>();
    }//Constructor

    public HighField(double difficulty){
        this.difficulty = difficulty;
        this.lowFields = new ArrayList<LowField>();
        if(difficulty >= 0 && difficulty < 3.4){
            this.efficiency = 3;
        }
        else if (difficulty >= 3.4 && difficulty < 6.7){
            this.efficiency = 2;
        }
        else{
            this.efficiency = 1;
        }
    }//Constructor



    public HighField(double difficulty, int efficiency){
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.lowFields = new ArrayList<LowField>();

    }//Constructor

    //with name constructors
    public HighField(String name){
        this.name = name;
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.lowFields = new ArrayList<LowField>();
    }//Constructor

    public HighField(String name, double difficulty){
        this.difficulty = difficulty;
        this.lowFields = new ArrayList<LowField>();
        this.name = name;
        if(difficulty >= 0 && difficulty < 3.4){
            this.efficiency = 3;
        }
        else if (difficulty >= 3.4 && difficulty < 6.7){
            this.efficiency = 2;
        }
        else{
            this.efficiency = 1;
        }
    }//Constructor



    public HighField(String name, double difficulty, int efficiency){
        this.name = name;
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.lowFields = new ArrayList<LowField>();

    }//Constructor
    // with name constructors
//without lowfields

//with

    public HighField(ArrayList<LowField> lowFields){
        this.name = "unnamed";
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.lowFields = lowFields;

    }//Constructor

    public HighField(double difficulty, ArrayList<LowField> lowFields){
        this.difficulty = difficulty;
        this.lowFields = lowFields;
        if(difficulty >= 0 && difficulty < 3.4){
            this.efficiency = 3;
        }
        else if (difficulty >= 3.4 && difficulty < 6.7){
            this.efficiency = 2;
        }
        else{
            this.efficiency = 1;
        }
    }//Constructor



    public HighField(double difficulty, int efficiency, ArrayList<LowField> lowFields){
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.lowFields = lowFields;

    }//Constructor

    //with name constructors
    public HighField(String name, ArrayList<LowField> lowFields){
        this.name = name;
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.lowFields = lowFields;
    }//Constructor

    public HighField(String name, double difficulty, ArrayList<LowField> lowFields){
        this.difficulty = difficulty;
        this.lowFields = lowFields;
        this.name = name;
        if(difficulty >= 0 && difficulty < 3.4){
            this.efficiency = 3;
        }
        else if (difficulty >= 3.4 && difficulty < 6.7){
            this.efficiency = 2;
        }
        else{
            this.efficiency = 1;
        }
    }//Constructor



    public HighField(String name, double difficulty, int efficiency, ArrayList<LowField> lowFields){
        this.name = name;
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.lowFields = lowFields;

    }//Constructor

//with
    public void setName(String name) {
        this.name = name;
    }//setName

    public String getName() {
        return name;
    }//getName

    public double getDifficulty() {
        return difficulty;
    }//getDifficulty

    public int getEfficiency() {
        return efficiency;
    }//getEfficiency

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }//setDifficulty

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }//setEfficiency

    public int getXp() {
        return xp;
    }//getXp

    public void setXp(int xp) {
        this.xp = xp;
    }//setXp

    public ArrayList<LowField> getLowFields() {
        return lowFields;
    }//getLowFields

    public void setLowFields(ArrayList<LowField> lowFields) {
        this.lowFields = lowFields;
    }//setLowFields

    @Override
    public String toString() {
        return "HighFieldActivity{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", efficiency=" + efficiency +
                ", number of LF=" + numberOfLowFields() +
                ", total xp=" + xp +
                '}';
    }//toString

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == HighField.class) {
            if (((HighField) obj).getName() == name && ((HighField) obj).getDifficulty() == difficulty) { // same dif and name
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }//equals

    public int numberOfLowFields(){
        return lowFields.size();
    }

    public void addLowField(LowField lf){
        lowFields.add(lf);
    }//addLowField

    public double averageDifficulty(){
        double sum = 0;
        double result;
        for(LowField lf: lowFields){
            sum += lf.getDifficulty();
        }
        result = sum/(double)lowFields.size();

        return result;
    }//averageDifficulty

    public int averageEfficiency() {
        double sum = 0;
        int result;
        for(LowField lf: lowFields){
            sum += lf.getEfficiency();
        }
        result = round(sum/(double)lowFields.size());

        return result;
    }//averageEfficiency

    public int round(double num){
        if(num < 0){
            return (int)Math.ceil(num - 0.5);
        }else{
            return (int)Math.floor(num + 0.5);
        }
    }//round

    public int determineXp(){
        int individualXp = 0;
        int finalXp = 0;
        double multiplier = 20;

        for(LowField lf: lowFields){
            int sum = round(lf.getDifficulty() * (double)lf.getEfficiency() * multiplier);

            if (!lf.isComplete){
                sum = sum/2;
            }
            individualXp += sum;
        }//foreach

        finalXp = individualXp * numberOfLowFields();

        this.xp = finalXp; // maybe delete #######

        return finalXp;
    }//determineXp

    /*
    It changes the difficulty and efficiency to the averages of the lowfields
     */
    public void heavyUpdate(){
        determineXp();
        this.efficiency = averageEfficiency();
        this.difficulty = averageDifficulty();
    }//heavyUpdate
    /*
    It only updates xp
     */
    public void lightUpdate(){
        determineXp();
    }//lightUpdate

    /*
    Should return a single (spaced) string with all the names of the lowfields
     */
    public String listNames(){
        String result = "";
        for (LowField lf:lowFields){
            result += lf.getName() + "\n";
        }
        return result;
    }//listNames


    public static void uploadHighField(Context fileContext, Field field) throws IOException {


        String name = field.getFieldName()+"1.ser";
        FileOutputStream fileOut = fileContext.openFileOutput(name, Activity.MODE_PRIVATE); // instead of:=> new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);


        out.reset();

        out.writeObject(field.getHighFields());


        //out.writeObject(dummy);//dummyTest


        out.close();
        fileOut.close();


    }//uploadField

    public static ArrayList<HighField> readHighField(Context fileContext, String filename) throws IOException, ClassNotFoundException {
        ArrayList<HighField> obj = null;
        String name = "/data/data/com.example.tierlevel/files/"+filename+"1.ser";
        File f = new File(name);
        //System.out.println("TESTTTT");///data/data/com.example.tierlevel/files/Physics.ser
        System.out.println(name);
        if (f.isFile()) {
            //System.out.println("HEKOOOPP");
            //FileInputStream fileIn = fileContext.openFileInput(name);// instead of:=> new FileInputStream(filename);
            FileInputStream fileIn = new FileInputStream (new File(name));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = (ArrayList<HighField>) in.readObject();
            in.close();
            fileIn.close();
        }
        return obj;
    }//readField

    public LowField findLowFieldByName(String name){
        for(LowField lf: lowFields){
            if(lf.getName().equals(name)){
                return lf;
            }
        }
        return null;
    }

//    public static void uploadField(Context fileContext, Field field) throws IOException {
////        ArrayList<HighField> dummyHfList = new ArrayList<HighField>();
////        HighField dummyHf = new HighField("DummyHf");
////        dummyHfList.add(dummyHf);
//        //Field dummy = new Field("dummy");
//        //LowField dummy = new LowField("Dummy");
//
//        String name = field.getFieldName()+".ser";
//        FileOutputStream fileOut =  fileContext.openFileOutput(name, Activity.MODE_PRIVATE); // instead of:=> new FileOutputStream(filename);
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        //out.reset();//CHANGED MAY NOT WORK, edit: it doesn't.
//        out.writeObject(field);
//        //out.writeObject(dummy);//dummyTest
//
//
//        out.close();
//        fileOut.close();
//
//
//    }//uploadField

}//HighFieldActivity
