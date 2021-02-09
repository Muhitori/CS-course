package com.shpp.p2p.cs.kturevich.assignment2;

import java.awt.*;

/** Program for drawing Flags (flag of france in my case). */
public class Assignment2Part4 extends BaseDrawer {

    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        //Attributes of the flag, Its scaling by window
        double width = getWidth() / 4.0;
        double height = getHeight() * 0.75;

        double x = getWidth() / 2.0 - width / 2;
        double y = getHeight() / 2.0 - height / 2;

        /** Borders is black because this way flag looks more SOLID :)) */

        //left fragment of the flag
        add(drawRectangle(x - width, y, width, height,
                             Color.BLUE, Color.BLACK));

        //central fragment of the flag
        add(drawRectangle(x, y, width, height,
                            Color.WHITE, Color.BLACK));

        //right fragment of the flag
        add(drawRectangle(x + width, y, width, height,
                            Color.RED, Color.BLACK));

        add(printText("Flag of France", getWidth(), getHeight()));
    }

}
