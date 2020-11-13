package Reviews.yvashchenia.assignment1;

import com.shpp.karel.KarelTheRobot;

//Karel has to build pillars of beepers.
public class Assignment1Part2 extends KarelTheRobot {

    //The main function from which the program is entered.
    public void run() throws Exception {

        //The decomposition of the program is described inside the run function.
        throwBeeper();
        if (frontIsClear()) {
            moveToTheNextPillar();

            //In order not to write additional code, I use recursion here.
            run();
        }
    }

    //Karel, from the starting position, goes to the beginning of the pillar and puts beepers if they are not there.
    private void throwBeeper() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }

        /**
         * This check is needed for the last column.
         * If there is no beeper, then Karel puts it there.
         */
        if (noBeepersPresent()) {
            putBeeper();
        }

        /**
         * When Karel has placed the beeps and there is no wall to his right,
         * another function is called.
         */
        if (rightIsClear()) {
            movementToTheStartingPosition();
        }
    }

    //After Karel has built the pillar, he moves to its original position.
    private void movementToTheStartingPosition() throws Exception {
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnLeft();
    }

    //This function indicates the distance between the posts.
    private void moveToTheNextPillar() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    //Karel's turn by 180 degrees.
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}