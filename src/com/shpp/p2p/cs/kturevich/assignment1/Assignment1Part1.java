package com.shpp.p2p.cs.kturevich.assignment1;

/**
 * Step on line, where newspaper lies
 * and go back through same path
 */
public class Assignment1Part1 extends SuperKarel {

    //main method of the Karel
    public void run () throws Exception {
        goForNewspaperAndTakeIt();
        goToStart();
    }

    //Karel steps on line where newspaper lies, go fot it and take it
    private void goForNewspaperAndTakeIt () throws Exception {
        stepRight();
        moveToNewspaper();
        pickBeeper();
    }

    //Karel looks out of the house, so he turns around
    //and go to start by the same path
    private void goToStart() throws Exception {
        turnAround();
        moveToWall();
        stepRight();
    }

    private void stepRight() throws Exception {
        turnRight();
        move();
        turnLeft();
    }

    private void moveToNewspaper() throws Exception {
        while(noBeepersPresent()){
            move();
        }
    }

    private void moveToWall() throws Exception {
        while (frontIsClear()){
            move();
        }
    }

}
