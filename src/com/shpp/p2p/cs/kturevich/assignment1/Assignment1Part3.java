package com.shpp.p2p.cs.kturevich.assignment1;

/**
 * Main idea of the algorithm is to mark bottom line
 * then collect 1 beeper from each side while they exist
 * when Karel picks last beeper, he steps backward and put beeper there
 */
public class Assignment1Part3 extends SuperKarel {

    //main method of the Karel
    public void run() throws Exception {
        findCenter();
    }

    //main method of algorithm
    private void findCenter() throws Exception {
        if (frontIsClear()) {
            //move forward, which allow us
            //to skip 1 iteration of marking
            move();

            markLine();
            while (beepersPresent()) {
                removeOneBeeper();
            }
        }

        //at that moment Karel is in the center
        //of the bottom line and marks the cell
        putBeeper();
    }

    //marks bottom line of the field, and turns Karel around
    //to that he will not face a wall
    private void markLine() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
            putBeeper();
        }
        turnAround();
    }

    //remove 1 beeper from the field and go to the opposite side beeper
    private void removeOneBeeper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();

            //step forward, so that we chek if beepers present on the map
            move();

            goToNext();
        }
    }

    //If beepers present, Karel moves to the last beeper of the opposite side
    //if no beepers present,Karel needs to step bask, because earlier he moves forward
    private void goToNext() throws Exception {
        while (beepersPresent()) {
            move();
        }
        stepBack();
    }

    //Karel changes his direction to opposite and moves backward
    private void stepBack() throws Exception {
        turnAround();
        move();
    }

}
