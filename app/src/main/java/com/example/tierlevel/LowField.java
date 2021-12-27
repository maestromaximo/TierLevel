package com.example.tierlevel;

import java.io.Serializable;

public class LowField implements Serializable {
    String name;
    double difficulty; // 1 - 10
    int efficiency; // 1-3 int
    boolean isComplete;

    public LowField(){
        this.name = "unnamed";
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.isComplete = false;
    }//Constructor

    public LowField(String name, double difficulty, int efficiency){
        this.difficulty = difficulty;
        this.isComplete = false;
        this.efficiency = efficiency;
        this.name = name;
        this.isComplete = false;
    }

    public LowField(double difficulty){
        this.difficulty = difficulty;
        this.isComplete = false;
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

    public LowField(double difficulty, boolean isComplete){
        this.difficulty = difficulty;
        this.isComplete = isComplete;
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

    public LowField(double difficulty, int efficiency, boolean isComplete){
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.isComplete = isComplete;

    }//Constructor

    //with name constructors
    public LowField(String name){
        this.name = name;
        this.difficulty = 4.5;
        this.efficiency = 2;
        this.isComplete = false;
    }//Constructor

    public LowField(String name, double difficulty){
        this.difficulty = difficulty;
        this.isComplete = false;
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

    public LowField(String name, double difficulty, boolean isComplete){
        this.name = name;
        this.difficulty = difficulty;
        this.isComplete = isComplete;
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

    public LowField(String name, double difficulty, int efficiency, boolean isComplete){
        this.name = name;
        this.difficulty = difficulty;
        this.efficiency = efficiency;
        this.isComplete = isComplete;

    }//Constructor


    // with name constructors

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

    public boolean getIsComplete() {
        return isComplete;
    }//getIsComplete

    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }//setIsComplete

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }//setDifficulty

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }//setEfficiency

    @Override
    public String toString() {
        return "com.example.tierlevel.LowField{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", efficiency=" + efficiency +
                ", isComplete=" + isComplete +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == LowField.class) {
            if (((LowField) obj).getName() == name && ((LowField) obj).getDifficulty() == difficulty) { // same dif and name
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }//equals

    public void normalize(){
        if (difficulty > 10){
            difficulty = 10;
        }
        else if (difficulty < 0){
            difficulty = 0;
        }

        if (efficiency > 3){
            efficiency = 3;
        }
        else if (efficiency < 0){
            efficiency = 0;
        }
    }//normalize

}//com.example.tierlevel.LowField
