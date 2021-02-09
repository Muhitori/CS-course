package com.shpp.p2p.cs.kturevich.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    private static final int DAYS_IN_WEEK = 7;

    private static final int ALLOWED_MISSED_DAYS = 2;
    private static final int DAYS_FOR_BLOOD_PRESSURE = 3;

    private static final int NEEDED_MINUTES_PER_DAY = 30;
    private static final int MINUTES_FOR_BLOOD_PRESSURE = 40;


    public void run() {
        //Variables for counting
        int missedDays = 0;
        int daysForBloodPressure = 0;

        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            print("How many minutes did you do on day " + (i + 1) +"? ");
            int minutes = readInt();

            //Check negative minutes
            if (minutes < 0) {
                println("You entered number that lower than zero, today progress is set to zero!");
                minutes = 0;
            }

            //Count days without training
            if (minutes < NEEDED_MINUTES_PER_DAY){
                missedDays++;
            }

            //Count days when user trained more than 40 minutes
            if (minutes >= MINUTES_FOR_BLOOD_PRESSURE){
                daysForBloodPressure++;
            }
        }

        println("Cardiovacular health: ");
        cardioCheck(missedDays);

        println("Blood pressure: ");
        bloodPressureCheck(daysForBloodPressure);

    }


    private void cardioCheck(int missedDays) {

        //Calculate lacking days for cardio
        int neededDays = missedDays - ALLOWED_MISSED_DAYS;

        if (neededDays <= 0) {
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("You needed to train hard for at  " + neededDays + " more day(s) a week!");
        }
    }

    private void bloodPressureCheck(int daysForBloodPressure) {

        //Calculate lacking days for blood pressure
        int neededDays = DAYS_FOR_BLOOD_PRESSURE - daysForBloodPressure;

        if (neededDays <= 0) {
            println("Great job! You've done enough exercise to keep a low blood pressure");
        } else {
            println("You needed to train hard for at least " + neededDays + " more day(s) a week!");
        }
    }


}
