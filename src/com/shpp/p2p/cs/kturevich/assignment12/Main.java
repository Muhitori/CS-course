package com.shpp.p2p.cs.kturevich.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static boolean[][] booleans;
    private final static int MINIMAL_OBJECT_SIZE = 200;

    public static void main(String[] args) {
        String FILENAME = "";

        if(args.length == 0){
            FILENAME = "assets/13.png";
        } else {
            FILENAME = args[0];
            FILENAME = FILENAME.replaceAll(" ", "");
        }

        try {
            File file = new File(FILENAME);
            BufferedImage image = ImageIO.read(file);

            ImageProcessor imageProcessor = new ImageProcessor(image);

            booleans = imageProcessor.getBooleanArray();

            System.out.println(findSilhouettes());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    //Main method to count silhouettes
    private static int findSilhouettes() {
        int result = 0;

        for(int i = 0; i < booleans.length; i++){
            for (int j = 0; j < booleans[i].length; j++){
                //If object is bigger than minimal size - its not trash
                if(dfs(i ,j) > MINIMAL_OBJECT_SIZE) {
                    result++;
                }
            }
        }
        return result;
    }

    //Depth first search algorithm
    private static int dfs(int u, int v) {
        //if visited - return zero object size
        if (booleans[u][v])
            return 0;
        int objectSize = 1;
        booleans[u][v] = true;

        int[][] neighbours = {
                //top
                {u - 1, v - 1}, {u - 1, v}, {u - 1, v + 1},
                //left & right
                {u, v - 1}, {u, v + 1},
                //bottom
                {u + 1, v}, {u + 1, v + 1}, {u + 1, v - 1}
        };

        //check all neighbours
        for (int[] neighbour : neighbours) {
            int y = neighbour[0];
            int x = neighbour[1];

            //prevent out of bounds
            if (x >= booleans[u].length || y >= booleans.length || y < 0 || x < 0)
                continue;

            //if not visited
            if (!booleans[y][x]) {
                objectSize += dfs(y, x);
            }
        }
        return objectSize;
    }

}
