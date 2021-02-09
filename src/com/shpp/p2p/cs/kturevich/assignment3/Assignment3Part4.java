package com.shpp.p2p.cs.kturevich.assignment3;


import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    private static final int BRICK_HEIGHT = 40;
    private static final int BRICK_WIDTH = 60;
    private static final int BRICKS_IN_BASE = 10;

    //Added 1 for better visibility
    public static final int APPLICATION_HEIGHT = BRICK_HEIGHT * (BRICKS_IN_BASE + 1);
    public static final int APPLICATION_WIDTH = BRICK_WIDTH * BRICKS_IN_BASE;

    public static final int PAUSE_TIME = 2;

    public void run () {
        int x = 0;
        //Offset for first Brick
        int y = getHeight() - BRICK_HEIGHT;

        //Cycle is decremental because its started from bottom, where Bricks count maximal
        for (int i = BRICKS_IN_BASE; i >= 0; i--){
            drawRow(x, y, i);

            //Step up
            y -= BRICK_HEIGHT;
            //Horizontal offset for each row, because we delete Bricks
            x += BRICK_WIDTH / 2;
        }
    }

    private void drawRow(int x, int y, int i) {
        for (int j = 0; j < i; j++) {
            drawBrick(x, y);

            //Step right
            x += BRICK_WIDTH;
        }
    }

    protected void drawBrick(double x, double y) {
        GRect rectangle = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);

        //this LoC allows drawing with different colors -_-
        rectangle.setFilled(true);

        rectangle.setColor(Color.BLACK);
        rectangle.setFillColor(Color.BLUE);

        add(rectangle);
    }

}
