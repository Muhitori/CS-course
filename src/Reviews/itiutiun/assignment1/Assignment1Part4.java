package Reviews.itiutiun.assignment1;

import com.shpp.karel.KarelTheRobot;

/* start: Karel starts in the southwest corner and looks east and has a lot of beeper in his backpack
    task: 1-It is important that one of the placed beepers is in the southwest corner of the map
          2-The world has no beeper or walls
          3-The world may not be square, but it is at least as tall as it is wide
          4-the code should solve problems in worlds other than 8x8, for example, 3x5, etc.
          5-levels can also be 1 square wide or vice versa: only 1 square in height
          6-Karel can walk on the paths where he has already walked - do not try to come up with
          some kind of "optimized" algorithm
          7-It doesn't matter where Karel looks after he finishes his race
 need to: create a "checkerboard" with a beeper in a rectangular (NOT square!) world  */
public class Assignment1Part4 extends KarelTheRobot {

    // Karel builds a chessboard with the help of beepers
    public void run() throws Exception {
        if(frontIsBlocked()) {
            turnLeft();
        }
        construction();
        while (leftIsClear()) {
            moveForward();
            nextRow();
        }
    }

    // this is the method for chessboard construction
    private void construction() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    //  this is the method to move to the next row
    private void nextRow() throws Exception {
        if (beepersPresent()) {
            turnRight();
            move();
            turnRight();
            move();
            construction();
        } else if (noBeepersPresent()) {
            turnRight();
            move();
            turnRight();
            construction();
        }
    }

    //  this method to move straight
    private void moveForward() throws Exception {
        turnBack();
        while (frontIsClear()) {
            move();
        }
    }


    //  this is the method so that turn right
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    //  this is the method so that turn around
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }
}




