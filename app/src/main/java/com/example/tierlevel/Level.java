package com.example.tierlevel;

import java.io.Serializable;

public class Level implements Serializable {
    int currentLevel;
    int cumulativeXp;
    int remainingXp;
    int xpToNextLevel;
    String[] ranks = { "Wood",  "Rock", "Porcelain"," Iron", "Bronze",  "Silver",  " Gold",  "Diamond", "Platinum", "Obsidian" };


    public Level(){
        this.cumulativeXp = 0;
        this.currentLevel = determineCurrentLevel(); // should be 0
        this.remainingXp = determineRemainingXp(cumulativeXp);
    }//Constructor

    public Level(int cumulativeXp){
        this.cumulativeXp = cumulativeXp;
        this.currentLevel = determineCurrentLevel();
        this.remainingXp = determineRemainingXp(cumulativeXp);
    }//Constructor

    public void updateLv(){
        currentLevel = determineCurrentLevel();
        remainingXp = determineRemainingXp(cumulativeXp);
        xpToNextLevel = determineXpToNext(cumulativeXp);
    }//updateLv

    public int getCumulativeXp() {
        return cumulativeXp;
    }//getCumulativeXp

    public int getCurrentLevel() {
        return currentLevel;
    }//getCurrentLevel

    public int getRemainingXp() {
        return remainingXp;
    }//getRemainingXp

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }//setXpToNextLevel

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }//getXpToNextLevel

    public void setRemainingXp(int remainingXp) {
        this.remainingXp = remainingXp;
    }//setRemainingXp

    public void setCumulativeXp(int cumulativeXp) {
        this.cumulativeXp = cumulativeXp;
    }//setCumulativeXp

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }//setCurrentLevel

    @Override
    public String toString() {
        return "com.example.tierlevel.Level{" +
                "currentLevel=" + currentLevel +
                ", cumulativeXp=" + cumulativeXp +
                ", remainingXp=" + remainingXp +
                ", xpToNextLevel=" + xpToNextLevel +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == Level.class) {
            if (((Level) obj).getCurrentLevel() == currentLevel) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }//equals

    public int round(double num){
        if(num < 0){
            return (int)Math.ceil(num - 0.5);
        }else{
            return (int)Math.floor(num + 0.5);
        }
    }//round
    /*
    Grants xp and automatically updates stats
     */
    public void grantXp(int amountToGrant){
        cumulativeXp += amountToGrant;
        updateLv();
    }//grantXp
    /*
    Removes xp and automatically updates stats
     */
    public void takeXp(int amountToTakeAway){
        cumulativeXp -= amountToTakeAway;
        updateLv();
    }//takeXp

    public void levelUpToLevel(int level){
        if(level > 0){
            int newXp = xpForLevel(level);
            cumulativeXp = newXp;
            updateLv();
        }
    }//levelUpToLevel

    public void levelUp(){
        int newLv = currentLevel + 1;
        int newXp = xpForLevel(newLv);
        cumulativeXp = newXp;
        updateLv();
    }//levelUp

    public void levelDown(){
        if(currentLevel > 0) {
            int newLv = currentLevel - 1;
            int newXp = xpForLevel(newLv);
            cumulativeXp = newXp;
            updateLv();
        }
        else{
            //do nothing
        }
    }//levelDown

    public int determineXpToNext(int cumXp){

        int level = determineLevel(cumXp);

        int freeXp = determineRemainingXp(cumXp);

        int xpToNext = xpInLevel(level) - freeXp;

        return xpToNext;

    }

    public int determineCurrentLevel() {
        int level = determineLevel(cumulativeXp);
        return level;
    }//determineCurrentLevel
    /*
    Single xp, in level; only one level
     */
    public int xpInLevel(int level){
        if (level <= 0){// maybe delete later
            return xpForLevel(0);
        }else{
            int lowLevel = xpForLevel(level);
            int highLevel = xpForLevel(level + 1);
            int difference = highLevel - lowLevel;
            return difference;
        }
    }
    /*
    All the Cumulative xp required to be at this level
     */
    public  int xpForLevel(int level){
        double coefficientOfGrowth = 0.8;
        double exponentOfGrowth = 3;
        double xp = coefficientOfGrowth * Math.pow(level, exponentOfGrowth);
        int finalXp = round(xp);

        return finalXp;

    }

    /*
    Given xp determine level (truncates)
     */
    public int determineLevel(int xp){

        double coefficientOfGrowth = 0.8;
        //double exponentOfGrowth = 3;

        int level = (int) Math.cbrt((double) xp / coefficientOfGrowth);

        return level;

    }//determineLevel

    /*
    Determine the remaining xp, must input all xp of the object
     */
    public int determineRemainingXp(int allXp){
        int level = determineLevel(allXp);
        int xpForLevel = xpForLevel(level);

        int freeXp = allXp - xpForLevel;

        return freeXp;

    }//determineRemainingXp

    public String convertToRank(){

        updateLv();
        int lv = ((int)currentLevel/10) - (currentLevel % 10);
        if (lv < 0){
            lv = 0;
        }

        return ranks[lv];

    }

    public String convertToRank(int xp){

        int le = determineLevel(xp);
        int lv = ((int)le/10) - (le % 10);
        if (lv < 0){
            lv = 0;
        }
        return ranks[lv];

    }

}//com.example.tierlevel.Level
