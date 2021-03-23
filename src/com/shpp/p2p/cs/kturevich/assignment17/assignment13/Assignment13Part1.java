package com.shpp.p2p.cs.kturevich.assignment17.assignment13;

import com.shpp.p2p.cs.kturevich.assignment16.MyQueue;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyLinkedList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Assignment13Part1 {
    private int result;
    private static boolean[][] booleans;
    private static final MyLinkedList<Point> queue = new MyLinkedList<>();
    private final static int MINIMAL_OBJECT_SIZE = 200;

    public Assignment13Part1(String[] args) {
        long start = System.currentTimeMillis();
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

            result = findSilhouettes();
            System.out.println("Finding silhouettes took " + (System.currentTimeMillis() - start) +" ms");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    //Main method to count silhouettes
    private static int findSilhouettes() {
        int result = 0;

        for(int i = 0; i < booleans.length; i++){
            for (int j = 0; j < booleans[i].length; j++){
                //If object is not background...
                if (!booleans[i][j]) {
                    //...and object is not trash
                    if (bfs(i, j) > MINIMAL_OBJECT_SIZE) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    //Wide first search algorithm
    private static int bfs(int u, int v) {
        int objectSize = 1;

        queue.add(new Point(v, u));
        booleans[u][v] = true;

        while (!queue.isEmpty()) {
            objectSize++;
            Point point = queue.poll();
            checkChildren(point.y, point.x);
        }

        return objectSize;
    }

    //Method to fill queue with children of this node
    private static void checkChildren(int u, int v) {

        int[][] neighbours = {
                //top
                {u - 1, v},
                //left & right
                {u, v - 1}, {u, v + 1},
                //bottom
                {u + 1, v}
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
                queue.add(new Point(x,y));
                booleans[y][x] = true;
            }
        }
    }

    public int getResult() {
        return result;
    }
}
