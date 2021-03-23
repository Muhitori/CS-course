package com.shpp.p2p.cs.kturevich.assignment17.assignment11;

import com.shpp.p2p.cs.kturevich.assignment17.MyHashMap;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;

public class Assignment11Part1 {

    private double result;

    public Assignment11Part1(String[] args) {
        MyHashMap<String, Double> variables = new MyHashMap<>();

        try {
            long start = System.currentTimeMillis();
            //Parse formula
            Tokenizer tokenizer = new Tokenizer(args[0].replaceAll(" ", ""));
            MyArrayList<String> formula = tokenizer.getFormulaList();

            parserVariables(args, variables);

            Calculator calculator = new Calculator(formula, variables);
            result = calculator.getResult();
            System.out.println("Calculating took " + (System.currentTimeMillis() - start) +" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parserVariables(String[] args, MyHashMap<String, Double> variables) {
        for (int i = 1; i < args.length; i++) {
            parserVariable(args[i], variables);
        }
    }

    private static void parserVariable(String variable, MyHashMap<String,Double> variables) {
        variable = variable.replaceAll(" ", "");
        String[] tmp = variable.split("=");
        variables.put(tmp[0], Double.parseDouble(tmp[1]));
    }

    public double getResult() {
        return result;
    }
}
