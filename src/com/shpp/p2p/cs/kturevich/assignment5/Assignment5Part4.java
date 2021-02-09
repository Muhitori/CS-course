package com.shpp.p2p.cs.kturevich.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    
    public void run() {
        String fileName = "assets/random-words.csv";

        println("Enter index of column:");
        int column = readInt();

        ArrayList<String> selectedColumn = extractColumn(fileName, column);

        for (String word : selectedColumn) {
            println(word);
        }
    }

    private ArrayList<String> extractColumn(String fileName, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<ArrayList<String>> parsedLines = parseFile(fileName);

        for (ArrayList<String> line : parsedLines) {
            result.add(line.get(columnIndex));
        }

        return result;
    }

    //Returns array of lines which contains separated elements
    private ArrayList<ArrayList<String>> parseFile (String fileName) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            while (line != null) {
                result.add(fieldsIn(line));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> result = new ArrayList<>();
        String field = "";

        char coma = ',';
        char quote = '\"';

        for (int i = 0; i < line.length(); i++) {
            //Adding letters to element until find coma
            while (line.charAt(i) != coma) {
                //If find quote - adding letters to element until find another coma
                if (line.charAt(i) == quote) {
                    do {
                        field += line.charAt(i);
                        i++;
                    } while (line.charAt(i) != quote);
                }
                //Add characters or last quote to element
                field += line.charAt(i);
                i++;

                //If element reaches end of line - break
                if (i == line.length())
                    break;
            }
            result.add(field);
            field = "";
        }
        return result;
    }

}
