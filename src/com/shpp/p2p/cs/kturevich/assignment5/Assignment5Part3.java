package com.shpp.p2p.cs.kturevich.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {

    public void run() {
        String fileName = "assets/en-dictionary.txt";
        //Read words to array to avoid additional reading
        ArrayList<String> words = readWords(fileName);
        while (true) {
            println("Enter \"q\" to quit!!!");

            println("Enter letters: ");
            String letters = readLine().toLowerCase();

            if (letters.equals("q")) {
                break;
            }

            findWords(letters, words);
        }
    }

    private ArrayList<String> readWords(String fileName) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            println("Error while reading!");
        }

        return result;
    }

    private void findWords(String letters, ArrayList<String> words) {
        for (String word : words) {
            if (containChars(word, letters)) {
                println(word);
            }
        }
    }

    //Returns "true" if word contains letters in specified order
    private boolean containChars(String word, String letters) {
        int index1 = word.indexOf(letters.charAt(0));
        int index2 = word.indexOf(letters.charAt(1));
        int index3 = word.indexOf(letters.charAt(2));

        //Check only first index, because another indexes must be bigger than first
        if (index1 == -1) {
            return false;
        }

        //Check letters order
        return index1 < index2 && index2 < index3;
    }

}
