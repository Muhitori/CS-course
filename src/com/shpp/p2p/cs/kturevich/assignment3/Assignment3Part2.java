package com.shpp.p2p.cs.kturevich.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    public void run() {
        println("Enter a number:");
        int number = readInt();

        //Storage for calculated number
        int newNumber;

        while(number != 1) {
            if (number % 2 == 0) {
                newNumber = number / 2;
                println(number + " is even so I take half: " + newNumber);
            } else {
                newNumber = number * 3 + 1;
                println(number + " is odd so I make 3n + 1: " + newNumber);
            }
            number = newNumber;
        }

        println("end");
    }

}
