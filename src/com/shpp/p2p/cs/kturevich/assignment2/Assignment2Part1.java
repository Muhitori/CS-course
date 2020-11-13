package com.shpp.p2p.cs.kturevich.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    public void run() {
        println("This program will solve equation of the form: a*(x^2) + b*x + c = 0 ");
        double a = readDouble("Enter a: ");
        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");
        solveEquation(a, b, c);
    }

    private void solveEquation(double a, double b, double c) {
        double D = b * b - (4 * a * c);
        if(D >= 0){
            double rootD = Math.sqrt(D);
            double x1 = (-b - rootD) / (2 * a);
            if(D > 0) {
                double x2 = (-b + rootD) / (2 * a);
                println("There are two roots: " + x1 + " and " + x2);
            } else {
                println("There is one root: " + x1);
            }
        } else {
            println("There are no real roots");
        }
    }
}
