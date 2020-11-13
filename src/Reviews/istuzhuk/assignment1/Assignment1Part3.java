package Reviews.istuzhuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {
    public void run() throws Exception {
        findACenter();
    }

    private void findACenter() throws Exception {
        //Put beepers to find the center of the line
        putBeepersToFindACenter();
    }

    //The next method allows you to put beepers to find the center of the row
    private void putBeepersToFindACenter() throws Exception {
        // mark the first cell with a beeper
        markTheFirstAndLastCell();

        // move to the other end of the line
        while (frontIsClear()) {
            move();
        }

        // mark the last cell with a beeper
        markTheFirstAndLastCell();

        //turn around and take a step forward
        turnAroundAndMakeAStep();

        //Find the center of the row
        findTheCenterOfTheRow();
    }

    //The method of turning the robot 180 degrees and make a steap
    private void turnAroundAndMakeAStep() throws Exception {
        turnAround();
        move();
    }

    //The method of turning the robot 180 degrees
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    //The method that allows the robot to mark the start and end cells in a line
    private void markTheFirstAndLastCell() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    //The method allows the robot to find the center of the line
    private void findTheCenterOfTheRow() throws Exception {
        //We move until there are no beepers under our feet
        while (noBeepersPresent()) {
            move();
        }
        //If the beeper is under your feet, we make a reversal and step forward to the next cell
        if (beepersPresent()) {
            turnAroundAndMakeAStep();
            //If there is a beeper on the next cell, then this is the center of the line
            //Then we call the method for clearing unnecessary beepers (clearExcessBeepers)
            if (beepersPresent()){
                clearExcessBeepers();
            }
            //Else we place the beepers until we find the center
            else {
                putBeeper();
                move();
                //Below is the recursion
                findTheCenterOfTheRow();
            }
        }
    }

    //The following method removes unnecessary beepers
    private void clearExcessBeepers() throws Exception {
        //The robot begins to move from the center of the line to the wall
        //while picking up unnecessary beepers
        pickingBeepers();

        //upon reaching the wall the robot turns 180 degrees
        turnAround();

        //the robot goes from the wall to the central cell of the row
        while(noBeepersPresent()){
            move();
        }
        //The robot crosses the central cell and moves towards the wall while collecting beepers
        pickingBeepers();
    }

    //The next method allows you to collect beepers by moving towards the wall
    private void pickingBeepers() throws Exception {
        while(frontIsClear()){
            move();
            pickBeeper();
        }
    }
}