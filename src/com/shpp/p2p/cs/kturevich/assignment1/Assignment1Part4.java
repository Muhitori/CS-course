package com.shpp.p2p.cs.kturevich.assignment1;

/**
 * The gist of the algorithm is that Karel checks the previous cell
 * to decide whether it needs to put a beeper on the current cell or not
 * and after filling each row Karel moves to its start, so that he need to
 * turn only one side to change row
 */
public class Assignment1Part4 extends SuperKarel {

    //main method of the Karel
    public void run() throws Exception {
        createChessBoard();
    }

    //main method of the algorithm
    private void createChessBoard() throws Exception {
        //put first beeper of the field
        putBeeper();

        while(leftIsClear()) {
            fillRow();
            moveToRowStart();
            goToAnotherRow();
        }

        //fill last row
        fillRow();
    }

    private void fillRow() throws Exception {
        while (frontIsClear()){
            //move to next cell
            move();
            decideCell();
        }
    }

    private void moveToRowStart() throws Exception {
        turnAround();
        while (frontIsClear()){
            move();
        }
    }

    private void goToAnotherRow() throws Exception {
        //because Karel looks at wal he needs to turn right
        //so that he can move to the next row
        turnRight();
        move();

        //decide first cell of the row
        decideCell();

        //Karel turns to row direction
        turnRight();
    }

    //Karel moves back and checks beeper of the previous cell
    //if beeper present, he put beeper on the current cell
    private void decideCell() throws Exception {
        moveBack();
        if(beepersPresent()){
            move();
        } else {
            move();
            putBeeper();
        }
    }

    private void moveBack() throws Exception {
        turnAround();
        if(frontIsClear()) {
            move();
        }
        turnAround();
    }

}