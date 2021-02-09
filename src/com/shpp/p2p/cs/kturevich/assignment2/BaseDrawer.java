package com.shpp.p2p.cs.kturevich.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class contains extended methods for drawing different shapes.
 */
public class BaseDrawer extends WindowProgram {

    /**
     * Function returns GOval object which is represented as circle.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     * @param radius radius of the circle.
     * @param fillColor color of the inner part of the circle.
     * @param borderColor color of the border of the circle.
     */
    protected GOval drawCircle(double x, double y, double radius, Color fillColor, Color borderColor) {
        GOval circle = new GOval(x, y, radius * 2, radius * 2);

        //this LoC allows drawing with different colors -_-
        circle.setFilled(true);

        circle.setColor(borderColor);
        circle.setFillColor(fillColor);

        return circle;
    }

    /**
     * Function returns GOval object which is represented as oval.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     * @param width width of the oval.
     * @param height height of the oval.
     * @param fillColor color of the inner part of the oval.
     * @param borderColor color of the border of the oval.
     */
    protected GOval drawOval(double x, double y, double width, double height, Color fillColor, Color borderColor) {
        GOval oval = new GOval(x, y, width, height);

        //this LoC allows drawing with different colors -_-
        oval.setFilled(true);

        oval.setColor(borderColor);
        oval.setFillColor(fillColor);

        return oval;
    }

    /**
     * Function returns GRect object which is represented as square.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     * @param edge a side of the square.
     * @param fillColor color of the inner part of the square.
     * @param borderColor color of the border of the square.
     */
    protected GRect drawSquare(double x, double y, double edge, Color fillColor, Color borderColor) {
        GRect square = new GRect(x, y, edge, edge);

        //this LoC allows drawing with different colors -_-
        square.setFilled(true);

        square.setColor(borderColor);
        square.setFillColor(fillColor);

        return square;
    }


    /**
     * Function returns GRect object which is represented as rectangle.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     * @param fillColor color of the inner part of the rectangle.
     * @param borderColor color of the border of the rectangle.
     */
    protected GRect drawRectangle(double x, double y, double width, double height, Color fillColor, Color borderColor) {
        GRect rectangle = new GRect(x, y, width, height);

        //this LoC allows drawing with different colors -_-
        rectangle.setFilled(true);

        rectangle.setColor(borderColor);
        rectangle.setFillColor(fillColor);


        return rectangle;
    }

    /**
     * Function returns GLabel object which is represented as text on window.
     * @param text the text that is printed.
     * @param x the x coordinate of the upper-left corner.
     * @param y the y coordinate of the upper-left corner.
     */
    protected GLabel printText(String text, double x, double y) {

        GLabel label = new GLabel(text);
        label.setFont(new Font(Font.SANS_SERIF, 0, 25));
        label.setLocation(x - label.getWidth(),
                         y - label.getHeight());
        return label;
    }
}
