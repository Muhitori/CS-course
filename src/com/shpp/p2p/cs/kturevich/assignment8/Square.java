package com.shpp.p2p.cs.kturevich.assignment8;

import acm.graphics.GRect;

import java.awt.*;

//Class to storage square
public class Square {
    private GRect square;
    private boolean clicked = false;
    private boolean reduced = true;

    Square(double x, double y, double size, Color color) {
        GRect square = new GRect(x, y, size, size);

        square.setFilled(true);
        square.setColor(color);

        this.square = square;
    }

    public double getX() {
        return this.square.getX();
    }

    public double getY() {
        return this.square.getY();
    }

    public GRect getSquare() {
        return this.square;
    }

    public Color getColor() {
        return this.square.getColor();
    }

    public double getSize() {
        return this.square.getHeight();
    }

    public void setSize(double size) {
        this.square.setSize(size, size);
    }

    public boolean isReduced() {
        return this.reduced;
    }

    public void toggleScaling() {
        this.reduced = !this.reduced;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void toggleState() {
        this.clicked = !this.clicked;
    }

}
