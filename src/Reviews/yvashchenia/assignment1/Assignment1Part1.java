package Reviews.yvashchenia.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Karel's task is to go to the door,
 * pick up the newspaper and return to its original state.
 */
public class Assignment1Part1 extends KarelTheRobot {

    //The main function from which the program is entered.
    public void run() throws Exception {

        //The decomposition of the program is described inside the run function.
        goingToTheNewspaper();
        pickUpTheNewspaper();
        returnToStartingPosition();
    }

    //In this function, Karel goes to raise the newspaper ...
    private void goingToTheNewspaper() throws Exception {
        turnRight();
        move();
        turnLeft();

        //...it goes on until it picks up the newspaper.
        while (noBeepersPresent()) {
            move();
        }
    }

    //Karel, picks up a newspaper.
    private void pickUpTheNewspaper() throws Exception {
        pickBeeper();
        turnAround();
    }

    //In this function, Karel, returns to the starting position already from the newspaper.
    private void returnToStartingPosition() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnRight();
        move();
        turnRight();
    }

    //Karel turn to the right.
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    //Karel's turn by 180 degrees.
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}