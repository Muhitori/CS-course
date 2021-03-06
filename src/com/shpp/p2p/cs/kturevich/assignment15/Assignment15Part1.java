package com.shpp.p2p.cs.kturevich.assignment15;

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment15Part1 {

    private static final String FILENAME = "assets/archive/empty.txt";

    public static void main(String[] args) throws Exception {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));

        try {
            if (argsList.size() > 1) {
                if (argsList.size() == 2) {
                    doubleElementsCases(argsList);
                } else {
                    if (argsList.contains("-u") && argsList.contains("-a"))
                        throw new Exception("Conflicting parameters");

                    String firstFile = argsList.get(1);
                    String secondFile = argsList.get(2);

                    if (argsList.contains("-a")) {
                        encode(firstFile, secondFile);
                    }

                    if (argsList.contains("-u")) {
                        decode(firstFile, secondFile);
                    }
                }
            } else {
                singleFIleProcessing(argsList, 0);
            }
        } catch (Exception e) {
            System.out.println("Error with: " + e.getMessage());
        }
    }

    //Actions if args takes 1 file
    public static void singleFIleProcessing (ArrayList<String> argsList, int fileIndex) throws Exception {
        String file = "";

        if (argsList.size() == 0) {
            file = FILENAME;
        } else {
            file = argsList.get(fileIndex);
        }

        int lastDotIndex = file.lastIndexOf('.');
        String extension = file.substring(file.lastIndexOf('.'));

        if (argsList.contains("-a")) {
            encode(file, file + ".par");
            return;
        }

        if (argsList.contains("-u")) {
            String filename = file.substring(0, lastDotIndex);
            decode(file, filename);
            return;
        }

        if (extension.equals(".par") && file.split("\\.").length == 2) {
            String filename = file.substring(0, lastDotIndex);
            decode(file, filename + ".uar");
            return;
        }

        if (extension.equals(".par")) {
            String filename = file.substring(0, lastDotIndex);
            decode(file, filename);
        } else {
            encode(file, file + ".par");
        }
    }

    //Conditions if 2 elements in args
    public static void doubleElementsCases(ArrayList<String> argsList) throws Exception {
        if (argsList.contains("-u") && argsList.contains("-a"))
            throw new Exception("Conflicting parameters");

        if (argsList.contains("-a") || argsList.contains("-u")) {
            singleFIleProcessing(argsList, 1);
            return;
        }

        String firstFile = argsList.get(0);
        String secondFile = argsList.get(1);

        int lastDotIndex = firstFile.lastIndexOf('.');
        String firstFileExtension = firstFile.substring(lastDotIndex);

        if (firstFileExtension.equals(".par")) {
            decode(firstFile, secondFile);
        } else {
            encode(firstFile, secondFile);
        }
    }

    public static void encode(String firstFile, String secondFile) throws Exception {
        Encoder encoder = new Encoder(firstFile, secondFile);
        encoder.main();
    }

    public static void decode(String firstFile, String secondFile) throws Exception {
        Decoder decoder = new Decoder(firstFile, secondFile);
        decoder.main();
    }
}
