package com.shpp.p2p.cs.kturevich.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/*Inspired by https://updatefaker.com/windows10/index.html*/
public class Assignment3Part6 extends WindowProgram {

    private static final int CIRCLE_RADIUS = 10;
    private static final int RADIUS = 100;

    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;

    public static final int PAUSE_TIME = 10;
    public static final int MILLISECONDS = 5000 * 3;
    public static final int DELAY = 100;

    public static final int SPEED = 2;


    public void run() {
        int timer = 0;
        //It was infinitive loop before ):
        for (int i = 0; timer < MILLISECONDS - PAUSE_TIME; i++) {
            //Decide which side is animated
            String side = i % 2 == 0 ? "left" : "right";

            timer += animateHemisphere(side, timer);
        }
        printText("Loading is complete!");
    }


    private int animateHemisphere(String side, int timer) {
        //If direction negative we are in the left hemisphere else in right
        int direction = chooseDirection(side);

        /* Loop starts with -radius so its covered drawing frame full
         * Loop works until radius + delay to make all balls disappear*/
        for (int i = -RADIUS; i <= RADIUS + DELAY; i+= SPEED) {
            removeAll();

            //Checking timer, subtract pause time to limit to 5 seconds
            if(timer > MILLISECONDS - PAUSE_TIME){
                println(timer);
                break;
            }

            drawBalls(i, direction);

            timer += PAUSE_TIME;
            pause(PAUSE_TIME);
        }
        return timer;
    }

    //Draw 5 circles, subtract "j" to have delay between circles
    private void drawBalls (int i, int direction) {
        for (int j = 0; j < DELAY; j += CIRCLE_RADIUS * 2) {
            double y = (i - j) * direction;
            double x = getX(RADIUS, y) * direction;

            //To prevent out of bounds
            if (y > -RADIUS && y < RADIUS) {
                drawCircle(x, y);
            }
        }
    }

    //Provides a value for chosen side
    private int chooseDirection(String side) {
        return side.equals("right") ? 1 : -1;
    }

    /*
     *  Calculates "x" coordinate so that we get curve
     *  x^2 + y^2 = r^2
     *  x^2 = r^2 - y^2
     *  x = sqrt(r^2 - y^2)
     */
    public double getX(double r, double y) {
        return Math.sqrt(Math.pow(r, 2) - Math.pow(y, 2));
    }

    /**
     * Draw circle around center of the window.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     */
    private void drawCircle(double x, double y) {
        double initX = getWidth() / 2.0;
        double initY = getHeight() / 2.0;
        GOval circle = new GOval( initX + x, initY + y, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

        circle.setFilled(true);
        circle.setColor(Color.BLUE);

        add(circle);
    }

    /**
     * Function prints text at the center of the window.
     * @param text the text that is printed.
     */
    protected void printText(String text) {
        double x = getWidth() / 2.0;
        double y = getHeight() / 2.0;

        GLabel label = new GLabel(text);

        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        label.setLocation(x - label.getWidth() / 2,
                         y - label.getHeight() / 2);
        add(label);
    }
}
