package Reviews.itiutiun.assignment1;

import com.shpp.karel.KarelTheRobot;

/*  start: Karel is in the house and the newspaper is on the street
     task: 1-move to the newspaper
           2-pick her up
           3-return to the starting point  */
public class Assignment1Part1 extends KarelTheRobot {

    /* this is the method where Karel
       goes for a newspaper, picks it up and returns home */
    public void run() throws Exception {
        goToBeeper();
        pickBeeper();
        goToHome();
    }

    //  this is the method so that turn right
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    //  this is the method so that go forward to the newspaper or home
    private void goForward() throws Exception {
        for (int i = 0; i < 4; i++){
            move();
        }
    }

    //  this is the method so that turn around
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }

    //  this is the method so that go for a newspaper
    private void goToBeeper() throws Exception {
        turnRight();
        move();
        turnLeft();
        goForward();
    }

    //  this is the method for going to the final destination
    private void goToHome() throws Exception {
        turnBack();
        goForward();
        turnRight();
        move();
        turnRight();
    }
}
