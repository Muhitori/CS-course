package com.shpp.p2p.cs.kturevich.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            try {
                String n1 = readLine("Enter first number:  ");
                String n2 = readLine("Enter second number: ");
                println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
                println();
            } catch (Exception e) {
                println("Exiting....");
                break;
            }
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        //Insert zeros until strings length are not equal
        while (n1.length() != n2.length()) {
            if (n1.length() >= n2.length()) {
                n2 = "0" + n2;
            } else {
                n1 = "0" + n1;
            }
        }

        String result = "";

        int buffer = 0;

        for(int i = n1.length() - 1; i >= 0; i--) {
            int sum = 0;

            if (buffer > 0) {
                sum = buffer;
                buffer = 0;
            }

            int number1 = Character.getNumericValue(n1.charAt(i));
            int number2 = Character.getNumericValue(n2.charAt(i));

            sum += number1 + number2;

            if (sum >= 10) {
                sum -= 10;
                buffer++;
            }

            result = sum + result;
        }

        if (buffer > 0) {
            result = buffer + result;
        }

        return result;
    }

}
