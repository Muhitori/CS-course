package com.shpp.p2p.cs.kturevich.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 10;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    /** Fps limiter (100fps) */
    private static final int PAUSE_TIME = 1000 / 100;

    /** Paddle object, don't exist at the start */
    GRect paddle = null;

    /** Variables for ball velocity, equal to zero at start */
    double vx = 0;
    double vy = 0;

    /** Number of bricks on field, equal to total number of brick at the start */
    int currentBricks = NBRICK_ROWS * NBRICKS_PER_ROW;

    /* Breakout main function */
    public void run() {
        addMouseListeners();

        paddle = createPaddle();

        //Calculate center of the window for ball
        double x = getWidth() / 2.0 - BALL_RADIUS;
        double y = getHeight() / 2.0 - BALL_RADIUS;

        GOval ball = createBall(x, y);

        //Drawing paddle and ball on the field in init state
        add(paddle);
        add(ball);

        createField();

        //Wait till user click, and then start the game
        waitForClick();
        startGame();

        //Count of missed balls
        int fails = 0;

        //Main loop of the game
        while (true) {
            move(ball);

            //Condition of winning
            if (currentBricks == 0) {
                removeAll();
                printText("YOU WIN!");
                break;
            }

            //Condition for missing balls
            if (ball.getY() > getHeight()) {
                fails++;

                //If missed all turns - lose
                if (fails == NTURNS) {
                    removeAll();
                    printText("YOU DIED!");
                    break;
                }

                ball.setLocation(x, y);
                waitForClick();
            }

            pause(PAUSE_TIME);
        }
        //Tired of useless comments -_-
    }


    private void startGame() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        vy = 3;
        if (rgen.nextBoolean(0.5)) {
                vx = -vx;
        }
    }


    private void createField() {
        double x = 0;
        double y = BRICK_Y_OFFSET;

        for (int i = 0; i < NBRICK_ROWS; i++) {
            drawRow(x, y, i);
            //Step down
            y += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    /**
     * Function that draws row of a certain index
     * @param x left edge of the row
     * @param y row height coordinates
     * @param i number of the row
     * */
    private void drawRow(double x, double y, int i) {
        //Richard Of York Gave Battle In Vain
        Color[] colors = {Color.RED, Color.ORANGE,
                        Color.YELLOW, Color.GREEN,
                        Color.BLUE, Color.CYAN, Color.MAGENTA};

        for (int j = 0; j < NBRICKS_PER_ROW; j++) {
            add(createRectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT, colors[i % 7]));
            //Step right
            x += BRICK_WIDTH + BRICK_SEP;
        }
    }

    /**
     * Function that moves object(ball) after checking collisions
     * @param ball Object type of {@link GOval}
     * */
    private void move(GOval ball) {
        double x = ball.getX();
        double y = ball.getY();

        collisionsCheck(x, y);

        ball.setLocation(x + vx, y + vy);
    }

    /**
     * Check for walls, bricks and paddle
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     */
    private void collisionsCheck(double x, double y) {
        edgesCheck(x, y);
        elementsCheck(x, y);
    }

    /**
     * Check for walls
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     */
    private void edgesCheck(double x, double y) {
        //Case horizontal collision - change horizontal velocity...
        if (x  <= 0 || x + BALL_RADIUS * 2 >= getWidth()) {
            vx = -vx;
        }

        //...or change vertical velocity
        if (y <= 0) {
            vy = -vy;
        }
    }


    /**
     * Check for bricks and paddle
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     */
    private void elementsCheck(double x, double y) {
        GObject collider = getCollidingObject(x, y);

        //No collider found
        if (collider == null) {
            return;
        }

        //Case collider found - change vertical velocity...
        if (collider == paddle) {
            vy = -vy;
        } else {
            vy = -vy;
            //...and remove collider and decrease brick count if its Brick
            remove(collider);
            currentBricks--;
        }
    }

    /**
     * Check for collider type
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     * @return {@link GObject} if collider found or null
     */
    private GObject getCollidingObject(double x, double y) {
        //Expect no collision by default
        GObject el = null;

        //Diameter of the ball
        int d = BALL_RADIUS * 2;

        /* Coordinates where collisions suspected
        *  coordinates are represent all corners of the ball  */
        double[][] coordinates = {
                                {x, y},
                                {x + d, y},
                                {x, y + d},
                                {x + d, y + d}};

        //Loops through all coordinates...
        for (double[] coordinate : coordinates) {
            GObject o = getElementAt(coordinate[0], coordinate[1]);

            //...as soon as found collision object - break loop
            if (o != null) {
                el = o;
                break;
            }
        }

        return el;
    }

    /**
     * @return Paddle object type of {@link GRect} that stores globally
     * */
    private GRect createPaddle() {
        return createRectangle(getWidth() / 2.0 - PADDLE_WIDTH / 2.0,
                             getHeight() - PADDLE_Y_OFFSET,
                                PADDLE_WIDTH, PADDLE_HEIGHT, Color.BLACK);
    }

    /**
     * Function that moves paddle together with the mouse
     * @param me link to object type {@link MouseEvent}
     * */
    public void mouseMoved (MouseEvent me) {
        double halfWidth = PADDLE_WIDTH / 2.0;

        //Check out of bounds horizontally for paddle
        if (0 <= me.getX() - halfWidth && me.getX() + halfWidth <= getWidth()) {
            paddle.setLocation(me.getX() - halfWidth, paddle.getY());
        }
    }

    /**
     * Function that creates ball object
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     * @return {@link GOval} object as ball
     */
    private GOval createBall(double x, double y) {
        GOval ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);

        ball.setFilled(true);
        //Ball always black
        ball.setColor(Color.BLACK);

        return ball;
    }

    /**
     * Function than creates brick of paddle of type {@link GRect}
     * @param x Coordinate upper-left corner of the ball
     * @param y Coordinate upper-left corner of the ball
     * @param width width of the paddle or brick
     * @param height height of the paddle or brick
     * @param color color of the paddle or brick as type {@link Color}
     * @return {@link GRect} object as paddle or brick
     */
    private GRect createRectangle(double x, double y, int width, int height, Color color) {
        GRect rectangle = new GRect(x, y, width, height);

        rectangle.setFilled(true);
        rectangle.setColor(color);

        return rectangle;
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