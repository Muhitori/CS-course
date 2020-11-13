package com.shpp.p2p.cs.kturevich.assignment1;

/**
 * Karel fills column and go back to cell start,
 * so that he always needs to turn one side(left)
 */
public class Assignment1Part2 extends SuperKarel {

    //main method of the Karel
    public void run() throws Exception{
        fillAllColumns();
    }

    //at start algorithm Karel stays at start of the first column
    private void fillAllColumns() throws Exception{
        while(frontIsClear()){
            fillColumn();
            goToColumnStart();
            changeColumn();
        }

        //fill last column
        fillColumn();
    }

    //method that leads Karel through column
    //and fills all empty slots
    private void fillColumn() throws Exception {
        //turn Karel to column direction
        turnLeft();

        while (frontIsClear()){
            fillCell();
            move();
        }

        fillCell();
    }

    private void goToColumnStart() throws Exception {
        //Because in front of Karel ia a wall he needs to turn around
        turnAround();

        while (frontIsClear()){
            move();
        }
        //turn left to prepare for line change
        turnLeft();
    }

    //because columns altering in 4, Karel needs 4 iterations of move
    private void changeColumn() throws Exception {
        for (int i = 0; i < 4; i++){
            move();
        }
    }

    //Karel checks cell, and if it dont contains beeper, puts it in
    private void fillCell() throws Exception {
        if(noBeepersPresent()){
            putBeeper();
        }
    }

}
