package Reviews.yvashchenia.assignment1;

import com.shpp.karel.KarelTheRobot;

//Here Karel must find the middle of the field
public class Assignment1Part3 extends KarelTheRobot {

    //The main function from which the program is entered.
    public void run() throws Exception {
        karelMovement();
    }

    //In this function, Karel starts, puts down the beeper ...
    private void karelMovement() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
        }

        //... here the function is called, in which Karel searches for the center of the field
        movingToTheCenterOfTheField();
    }

    //In this function, Karel, searches for the center of the field.
    private void movingToTheCenterOfTheField() throws Exception {
        turnAround();
        if (frontIsClear()) {
            move();
        }
        if (noBeepersPresent()) {
            putBeeper();
        }
        while (frontIsClear()) {
            move();
            if (beepersPresent()) {
                pickBeeper();

                /**
                 * Since the movements of finding the center of the field are the same,
                 * I use recursion here.
                 */
                movingToTheCenterOfTheField();
            }
        }
    }

    //Karel's turn by 180 degrees.
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}