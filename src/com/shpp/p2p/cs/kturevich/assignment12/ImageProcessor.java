package com.shpp.p2p.cs.kturevich.assignment12;

import java.awt.image.BufferedImage;

public class ImageProcessor {
    private final int COLOR_RANGE = 10000000;
    private final boolean[][] booleanArray;

    ImageProcessor(BufferedImage image) {
        int background = image.getRGB(0,0);
        this.booleanArray = toBooleanArray(background, image);
    }

    public boolean[][] getBooleanArray() {
        printArray();
        return booleanArray;
    }

    private boolean[][] toBooleanArray(int background, BufferedImage image) {
        boolean[][] result = new boolean[image.getHeight()][image.getWidth()];

        for(int i = 0; i < image.getHeight(); i++){
            for (int j = 0; j < image.getWidth(); j++){
                result[i][j] = decidePixel(image, background, j, i);
            }
        }
        return result;
    }

    //Decide if pixel if pixel equals background or not
    private boolean decidePixel(BufferedImage image, int background, int x, int y) {
        int pixelRGB = image.getRGB(x, y);
        return pixelRGB > (background - COLOR_RANGE) && pixelRGB < (background + COLOR_RANGE);
    }

    private void printArray() {
        for(int i = 0; i < booleanArray.length; i++){
            for (int j = 0; j < booleanArray[0].length; j++){
                System.out.print((booleanArray[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

}
