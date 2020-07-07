package com.example.ongoal;

public class Goal {
    private int currentDay, numberOfDays;
    private String data;
    private String goalID;

    public Goal() {
        this.currentDay = 0;
        this.numberOfDays = 0;
        this.data = "default";
        this.goalID = "default";
    }

    public Goal(int numberOfDays, String data, String goalID) {
        this.currentDay = 1;
        this.numberOfDays = numberOfDays;
        this.data = data;
        this.goalID = goalID;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentDay() {
        return this.currentDay;
    }

    public String getGoalID() {
        return this.goalID;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

}
