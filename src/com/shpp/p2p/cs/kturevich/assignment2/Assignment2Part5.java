package com.shpp.p2p.cs.kturevich.assignment2;

import java.awt.*;

/** Program for drawing task #5 -_- */
public class Assignment2Part5 extends BaseDrawer {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final int BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final int BOX_SPACING = 10;

    /* Margin between upper-left corners of the squares */
    private static final int STEP = BOX_SIZE + BOX_SPACING;

    /* General size of the whole picture */
    private static final int DRAWING_WIDTH = NUM_COLS * STEP;
    private static final int DRAWING_HEIGHT = NUM_ROWS * STEP;

    /* Size of the window depends on squares number. 1.15 was picked by random >_> */
    public static final int APPLICATION_WIDTH = (int) (DRAWING_WIDTH * 1.15);
    public static final int APPLICATION_HEIGHT = (int) (DRAWING_HEIGHT * 1.15);

    public void run() {
        double x = getWidth() / 2.0 - DRAWING_WIDTH / 2.0;
        double y = getHeight() / 2.0 - DRAWING_HEIGHT / 2.0;

        for(int i = 0; i < NUM_ROWS; i++) {
            drawRow(x, y);

            //shift down
            y += STEP;
        }
    }

    /**
     * @param x The x coordinate of the upper-left corner of the bounding box for the Row.
     * @param y The y coordinate of the upper-left corner of the bounding box for the Row.
     */
    private void drawRow(double x, double y){
        for (int j = 1; j <= NUM_COLS; j++) {
            add(drawSquare(x, y, BOX_SIZE, Color.BLACK, Color.BLACK));

            //shift to the right
            x += STEP;
        }
    }

}
