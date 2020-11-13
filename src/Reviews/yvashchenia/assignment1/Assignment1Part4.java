package Reviews.yvashchenia.assignment1;

import com.shpp.karel.KarelTheRobot;

//In this task, Karel has to build a chessboard out of beepers.
public class Assignment1Part4 extends KarelTheRobot {

    //The main function from which the program is entered.
    public void run() throws Exception {

        //This condition is fulfilled only in one 1x8 world
        if (frontIsBlocked() && facingEast()) {
            turnLeft();
        }
        movingAndBuildingTheChessboard();

        //Movement function and beeper placement.
        changeOfDirectionOfMovement();
        transitionFromWestToEast();
    }

    //In this function, Karel, moves and puts beepers.
    private void movingAndBuildingTheChessboard() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    //In this function, Karel, changes the direction of movement.
    private void changeOfDirectionOfMovement() throws Exception {
        if (leftIsClear() && noBeepersPresent()) {
            TurnFromEastToWest();
            movingAndBuildingTheChessboard();
        } else if (leftIsClear()) {
            TurnFromEastToWest();
            move();
            movingAndBuildingTheChessboard();
        }
    }

    //Moving Karel from west to east.
    private void transitionFromWestToEast() throws Exception {
        if (rightIsClear() && facingWest()) {
            turnRight();
            move();
            turnRight();

            //In order not to write out the code anymore, here I have written a main function.
            run();
        }
    }

    //Karel's movement from east to west
    private void TurnFromEastToWest() throws Exception {
        turnLeft();
        move();
        turnLeft();
    }

    //Karel turn to the right.
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}