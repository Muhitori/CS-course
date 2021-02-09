package com.shpp.p2p.cs.kturevich.assignment2;

import com.shpp.cs.a.console.TextProgram;

/** Program for quadratic equation solving. */
public class Assignment2Part1 extends TextProgram {

    public void run() {
        println("This program will solve equation of the form: a*(x^2) + b*x + c = 0 ");
        double a;

        //force user do not enter 0 as first coefficient -_-
        do {
            a = readDouble("Enter a: ");

            //if "a" is zero, equation does not exist
            if (a == 0) {
                println("This is not a quadratic equation");
            }
        } while(a == 0);

        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");

        solveEquation(a, b, c);
    }

    /**
     * Function print to console the result of solving a*(x^2) + b*x + c = 0 equation.
     */
    private void solveEquation(double a, double b, double c) {
        double D = getDiscriminant(a, b, c);

        //by condition not need to handle the case with negative discriminant
        if (D < 0){
            println("There are no real roots");
            return;
        }

        double rootOfD = Math.sqrt(D);
        double x1 = (-b - rootOfD) / (2 * a);

        if(D > 0) {
            double x2 = (-b + rootOfD) / (2 * a);
            println("There are two roots: " + x1 + " and " + x2);
        } else {
                println("There is one root: " + x1);
        }

    }

    private double getDiscriminant(double a, double b, double c){
        return b * b - (4 * a * c);
    }
}
