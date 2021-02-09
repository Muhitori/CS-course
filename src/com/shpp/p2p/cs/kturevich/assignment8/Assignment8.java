package com.shpp.p2p.cs.kturevich.assignment8;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Assignment8 extends WindowProgram implements Constants {

    //Mouse coordinats
    double mouseX, mouseY;

    //Squares on the field
    ArrayList<Square> squares = new ArrayList<>();

    public void run() {
        addMouseListeners();
        drawField();

        try {
            while (true) {
                //Create new thread to animate squares around mouse
                new AnimationThread(getNearestSquares());
                pause(PAUSE);
            }
        } catch (NullPointerException e) {
            println(e);
        }
    }

    //Method for drawing field
    public void drawField() {
        int y = 0;

        for(int i = 0; i < APPLICATION_HEIGHT; i++) {
            drawLine(i, y);
            y += SQUARE_SIZE;
        }
    }

    //Method draws line in the field
    private void drawLine(int i, int y) {
        int x = 0;

        for(int j = 0; j < APPLICATION_WIDTH; j++) {
            Color color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;

            Square square = new Square(x, y, SQUARE_SIZE, color);
            squares.add(square);
            add(square.getSquare());

            x += SQUARE_SIZE;
        }
    }

    //Method to get squares around mouse
    private ArrayList<Square> getNearestSquares() {
        ArrayList<Square> result = new ArrayList<>();

        if (mouseX == 0 && mouseY == 0){
            return result;
        }

        double range = NUMBER_OF_NEAREST_SQUARES * SQUARE_SIZE;

        //Search values
        double startX = mouseX - range;
        double startY = mouseY - range;

        double endX = mouseX + range;
        double endY = mouseY + range;

        for (Square square: squares) {
            if (square.getColor() != Color.BLACK) {
                continue;
            }

            if(square.getX() >= startX && square.getX() <= endX &&
            square.getY() >= startY && square.getY() <= endY) {
                result.add(square);
            }
        }

        return result;
    }

    public void mouseMoved (MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    public void mouseClicked (MouseEvent me) {
        GRect rect = (GRect) getElementAt(me.getX(), me.getY());

        if (rect == null) {
            return;
        }

        for (Square square: squares) {
            if (square.getX() == rect.getX() && square.getY() == rect.getY()) {
                square.toggleState();
            }
        }
    }
}

