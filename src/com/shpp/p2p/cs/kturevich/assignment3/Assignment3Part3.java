package com.shpp.p2p.cs.kturevich.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    public void run(){

        println("Enter base:");
        double base = readDouble();

        println("Enter exponent: ");
        int exponent = readInt();


        double result = raiseToPower(base, exponent);
        println("Number " + base + " in power " + exponent + " is " + result);
    }

    //Function that raise base number to the exponent degree
    private double raiseToPower(double base, int exponent) {
        //Result is 1 because multiplication by zero is always zero))
        int result = 1;

        //If exponent is negative transfer base to denominator
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }
}
