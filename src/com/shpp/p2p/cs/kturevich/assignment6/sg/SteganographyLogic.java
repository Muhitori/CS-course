package com.shpp.p2p.cs.kturevich.assignment6.sg;

import acm.graphics.*;

public class SteganographyLogic implements SteganographyConstants {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();

        boolean[][] result = new boolean[CANVAS_HEIGHT][CANVAS_WIDTH];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (GImage.getRed(pixels[i][j]) % 2 == 1) {
                    result[i][j] = true;
                } else {
                    result[i][j] = false;
                }
            }
        }

        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] result = new int[CANVAS_HEIGHT][CANVAS_WIDTH];

        int[][] sourcePixels = source.getPixelArray();

        for (int i = 0; i < sourcePixels.length; i++) {
            for (int j = 0; j < sourcePixels[i].length; j++) {
                int red = GImage.getRed(sourcePixels[i][j]);
                int green = GImage.getGreen(sourcePixels[i][j]);
                int blue = GImage.getBlue(sourcePixels[i][j]);

                red = hideInColor(message[i][j], red);

                result[i][j] = GImage.createRGBPixel(red, green, blue);
            }
        }

        return new GImage(result);
    }

    /**
     * Hide message in selected color.
     * @param isBlack boolean to check if current pixel is black
     * @param color color to hide
     * @return A pixel with new selected color
     * */
    private static int hideInColor(boolean isBlack, int color) {
        int newColor = color;
        if (isBlack) {
            if (color % 2 == 0) {
                newColor = changeColor(color);
            }
        } else {
            if (color % 2 == 1) {
                newColor = changeColor(color);
            }
        }
        return newColor;
    }

    /**
     * Adds or subtracts from selected color.
     * @param color color to hide
     * @return A morphed color.
     * */
    private static int changeColor(int color) {
        if (color < 255) {
            color++;
        }
        else {
            color--;
        }
        return color;
    }
}
