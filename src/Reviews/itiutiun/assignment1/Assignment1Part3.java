package Reviews.itiutiun.assignment1;

import com.shpp.karel.KarelTheRobot;

/* start: Karel starts in the southwest corner and looks east and has a lot of beeper in his backpack
    task: 1-Karel is from the very beginning on the lane where you will need to find the middle
          2-The world has no beeper or walls
          3-The world may not be square, but it is at least as tall as it is wide
          4-If the width of the world is odd, then Karel needs to put the beeper in the central cell,
          otherwise he needs to put it in one of the two central cells
          5-Karel can be scattered with additional beepers, but he needs to pick them up before he
          thinks that the task is completed
          6-The final view of the level should be with a single beeper in the center of the south lane
          7-It doesn't matter where Karel looks after he finishes his race
 need to: finding the middle  */
public class Assignment1Part3 extends KarelTheRobot {

    /* this is the method in which Karel need to find the
       middle of the row and leave 1 beeper there */
    public void run() throws Exception {
        goStraight();
        lookingForTheMiddle();
    }

    //  this is the method so that turn around
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }

    //  this is the method where Karel makes marks and puts beepers
    private void goStraight() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            move();
        }
    }

    /*  this is the method where Karel makes marks and puts beepers,
        then collects them and finds the middle  */
    private void lookingForTheMiddle() throws Exception {
        turnBack();
        if (frontIsClear()) {
            move();
            if(noBeepersPresent()) {
                putBeeper();
            }
        }
        while (frontIsClear()) {
            move();
            if (beepersPresent()) {
                pickBeeper();
                lookingForTheMiddle();
            }
        }
    }
}
