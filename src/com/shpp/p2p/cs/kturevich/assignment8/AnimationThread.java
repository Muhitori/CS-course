package com.shpp.p2p.cs.kturevich.assignment8;

import java.util.ArrayList;

class AnimationThread implements Runnable,Constants {

    ArrayList<Square> squares;
    Thread thread;

    AnimationThread(ArrayList<Square> squares) {
        this.squares = squares;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        for (Square square: squares) {
            animate(square);
        }
    }

    //One step of animation
    private void animate(Square square) {
        if (square == null || square.isClicked()) {
            return;
        }

        double size = square.getSize();

        if (size <= SQUARE_SIZE && !square.isReduced()) {
            size++;
            square.setSize(size);
        }

        if (size > SQUARE_SIZE || size < MINIMUM_SIZE) {
            square.toggleScaling();
        }

        if (size >= MINIMUM_SIZE && square.isReduced()) {
            size--;
            square.setSize(size);
        }
    }
}