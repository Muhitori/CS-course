package com.shpp.p2p.cs.kturevich.assignment2;

import java.awt.*;

/** Class that draws a caterpillar from circles :) */
public class Assignment2Part6 extends BaseDrawer {
    public static final int APPLICATION_HEIGHT = 300;
    public static final int APPLICATION_WIDTH = APPLICATION_HEIGHT * 3;

    public static final int NUMBER_OF_CIRCLES = 10;


    public void run() {
        double radius = getHeight() / 4.0;

        double x = 0;
        double y = getHeight() / 2.0 - radius;

        //spacing for circles
        double margin = radius / 4;
        double step = radius + margin;

        double verticalOffset = 40;

        for (int i = 0; i < NUMBER_OF_CIRCLES - 1; i++){
            add(drawCircle(x, y, radius, Color.GREEN, Color.RED));

            //shift to the right
            x += step;

            //decide vertical position
            y += (i % 2 != 0) ? verticalOffset : -verticalOffset;
        }
    }

}
