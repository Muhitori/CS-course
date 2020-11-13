package Reviews.itiutiun.assignment1;

import com.shpp.karel.KarelTheRobot;

/* start: Karel starts in the southwest corner and looks east and has a lot of beeper in his backpack
    task: 1-Columns are on the 1st, 5m, 9th row, etc., the last column will be close to the edge of the level
          2-There can be many columns, not 4 as in the example. For example, there may be only one column (the world is 1x8)
          3-The top of the column is marked with a wall, but you should not assume that all columns are five in height,
          and you should not think that all columns are the same height.
          4-Beepers should not be placed where they already lie
          5-the final position and direction of Karel's gaze is indifferent
 need to: Build columns  */
public class Assignment1Part2 extends KarelTheRobot{

    // this is the method in which Karel builds walls
    public void run() throws Exception {
        while (frontIsClear()) {
            buildingAWall();
            moveToNextWall();
        }
        buildingAWall();
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    // this is the method in which Karel builds walls from beepers
    private void buildingAWall() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        check();
    }

    //  check: the wall already exists or needs to be built
    private void check() throws Exception {
        if (rightIsClear()) {
            turnBack();
            if (noBeepersPresent()) {
                putBeeper();
            }
            while (frontIsClear()) {
                move();
            }
            turnLeft();
        }
    }

    //  this is the method so that turn around
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }

    //  it is a method to go from one wall to another
    private void moveToNextWall() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }
}
