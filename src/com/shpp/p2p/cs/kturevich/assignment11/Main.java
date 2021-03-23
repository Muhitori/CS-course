package com.shpp.p2p.cs.kturevich.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();

        try {
            long start = System.currentTimeMillis();
            //Parse formula
            Tokenizer tokenizer = new Tokenizer(args[0].replaceAll(" ", ""));
            ArrayList<String> formula = tokenizer.getFormulaList();

            parserVariables(args, variables);

            Calculator calculator = new Calculator(formula, variables);
            println("Result: " + calculator.getResult());
            System.out.println("Calculating took " + (System.currentTimeMillis() - start) +" ms");
        } catch (Exception e) {
            println("Exception: " + e.getMessage());
        }
    }

    private static void parserVariables(String[] args, HashMap<String, Double> variables) {
        for (int i = 1; i < args.length; i++) {
            parserVariable(args[i], variables);
        }
    }

    private static void parserVariable(String variable, HashMap<String,Double> variables) {
        variable = variable.replaceAll(" ", "");
        String[] tmp = variable.split("=");
        variables.put(tmp[0], Double.parseDouble(tmp[1]));
    }

    //Shortcut for println
    private static void println(String output) {
        System.out.println(output);
    }
}
