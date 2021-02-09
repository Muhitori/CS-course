package com.shpp.p2p.cs.kturevich.assignment2;

import java.awt.*;

/** Program for drawing task #2 -_- */
public class Assignment2Part2 extends BaseDrawer {

    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        double radius = getHeight() / 6.0;
        double diameter = radius * 2;

        //upper-left circle
        add(drawCircle(0, 0, radius,
                        Color.BLACK, Color.BLACK));

        /**Diameter was divided because drawing of the shape starts from the upper-left corner*/

        //upper-right circle
        add(drawCircle(getWidth() - diameter, 0, radius,
                        Color.BLACK, Color.BLACK));

        //lower-left circle
        add(drawCircle(0, getHeight() - diameter, radius,
                        Color.BLACK, Color.BLACK));

        //lower-right circle
        add(drawCircle(getWidth() - diameter, getHeight() - diameter, radius,
                        Color.BLACK, Color.BLACK));


        add(drawRectangle(radius, radius,
                    getWidth() - diameter,      //diameter was divided because upper-left corner
                    getHeight() - diameter,    //was offset by radius, that prevents outbound
                          Color.WHITE,
                          Color.WHITE));
    }

}
