package Reviews.istuzhuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        makeAChessBoard();
    }

    private void makeAChessBoard() throws Exception {
        //Put the beeper on the starting cell
        putBeeper();
        //move to the end of the row putting the beepers through one cell
        while (frontIsClear()){
            move();
            if (frontIsClear()) {
                move();
                makeAChessBoard();
            }
        }
        //if the robot is against the wall, move to the next row
        if(frontIsBlocked()) {
            moveToNextRow();
        }
    }
    //The following method lets to move to the next line
    private void moveToNextRow() throws Exception {
        if(facingEast()) {
            moveToAnEvenRow();
        }
        else if (facingWest()) {
            moveToAnOddRow();
        }
    }
    //This method lets to move to an even row
    private void moveToAnEvenRow() throws Exception {
        turnLeft();
        if (frontIsClear()) {
            //move to the second cell of the next row and run the makeAChessBoard() method
            move();
            turnLeft();
            move();
            makeAChessBoard();
        }
    }

    //This method lets to move to an odd row
    private void moveToAnOddRow() throws Exception {
        turnRight();
        if (frontIsClear()) {
            //move to the first cell of the row and put a beeper on it
            move();
            putBeeper();
            //turn right and cross one cell
            turnRight();
            move();
            move();
            //continue make a chess bord
            makeAChessBoard();
        }
    }

    //The following method allows the robot to turn right
    public void turnRight() throws Exception {
        for(int i = 0; i<3; i++)
            turnLeft();
    }
}
