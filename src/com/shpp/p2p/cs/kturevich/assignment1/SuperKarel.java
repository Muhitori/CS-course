package com.shpp.p2p.cs.kturevich.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Class that contains additional functionality for Karel
 */
public class SuperKarel extends KarelTheRobot {

    public void turnRight() throws Exception {
       for (int i = 0; i < 3; i++) {
           turnLeft();
       }
    }

    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

}
