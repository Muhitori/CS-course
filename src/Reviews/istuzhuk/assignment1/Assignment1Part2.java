package Reviews.istuzhuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {
    public void run () throws Exception{
        buildTheWall();
    }

    //This method lets to build rows of the walls
    private void buildTheWall() throws Exception {
        while(frontIsClear()){
            LockAndPutStones();
            turnAround();
            moveToNextRow();
        }
        //This condition works for the first and the last row
        //for example map 1x8
        if(frontIsBlocked()){
            LockAndPutStones();
        }
    }

    //The next method involves the search and installation of "Beeper", in those cells where they were not found
    private void LockAndPutStones() throws Exception {
        turnLeft();
        //The cycle works until the robot reaches the wall
        while(!frontIsBlocked()){
            //Condition of absence of "Beeper"
            //the beeper is installed if it is absent on the cell
            if(noBeepersPresent()){
                putBeeper();
            }
            //in case of detection of the "Beeper", the robot takes a step to the next cell
            else {
                move();
            }
        }
        //The bottom condition determines whether the Beeper is present on the last square in front of the wall
        if (frontIsBlocked()){
            if(noBeepersPresent()){
                putBeeper();
            }
        }
    }

    //The following method allows the robot to turn around from the "top of the column"
    //and go down to go to the next "column"
    private void turnAround() throws Exception {
        turnRight();
        move();
        turnRight();
        while(frontIsClear()){
            move();
        }
    }

    //The following method allows the robot to turn right
    public void turnRight() throws Exception {
        for(int i = 0; i<3; i++)
            turnLeft();
    }

    //The moveToNextRow method allows the robot to move to a row, in this case "column"
    private void moveToNextRow() throws Exception {
        turnLeft();
        for (int i = 0; i < 3; i++) {
            if (frontIsClear())
                move();
        }
    }
}
