package com.shpp.p2p.cs.kturevich.assignment17.assignment11;

import com.shpp.p2p.cs.kturevich.assignment17.MyHashMap;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to calculate expression
 * */
public class Calculator {
    private final MyHashMap<String, Double> variables;
    private double result;
    //Operators with one operand
    private final String unaryOperators = "sin, cos, tan, atan, log10, log2, sqrt";
    //Operators, a priority of operation depends on it index in array
    private final String[] operators = {unaryOperators, "^", "/, *", "-, +"};

    Calculator(MyArrayList<String> formula, MyHashMap<String, Double> variables) throws Exception {
        this.variables = variables;
        calculate(formula);
    }

    public double getResult() { return result; }

    private void calculate(MyArrayList<String> formula) throws Exception {
        for (String s : operators) {
            action(formula, s);
        }
    }

    //Perform action with operator
    private void action(MyArrayList<String> formula, String operator) throws Exception {
        for(int i = 0; i < formula.size() - 1 ; i++) {
            String symbol = formula.get(i);

            if (symbol.equals("(")) {
                proceedCalculation(i, formula);
            }

            //Prevent that operator is 2 or 10 (in case log2 & log10)
            // and matching variable as part of operator
            if(operator.contains(symbol)  && !isNumber(symbol) && !variables.containsKey(symbol)) {
                result = 0;

                //check for neighbour bracket
                if(formula.get(i+1).equals("(")) {
                    proceedCalculation(i+1, formula);
                    //Decrement because we delete more than 1 element above (preventing out of bounds)
                    i--;
                    continue;
                }

                //check for unary operator
                if(unaryOperators.contains(symbol)){
                    double operand = getValue(formula.get(i + 1));
                    result += execute(symbol, operand);
                    unaryUpdate(formula, i, result);
                    continue;
                }

                //Exponent need his own calculation process if it not single in this scope
                if(symbol.equals("^") && getLastExpIndex(formula) != -1) {
                    proceedExponent(getLastExpIndex(formula),formula);
                    //Decrement because we delete more than 1 element above (preventing out of bounds)
                    i--;
                    continue;
                }

                double leftOperand = getValue(formula.get(i - 1));
                double rightOperand = getValue(formula.get(i + 1));


                result += execute(leftOperand, symbol, rightOperand);

                //Decrement because we delete more than 1 element above (preventing out of bounds)
                //In this case i - index of left operand
                i--;
                updateFormula(formula, i, result);
            }

            if (symbol.equals(")")) {
                return;
            }
        }
    }

    //Method for descent, it will remove wrapping brackets when 1 number inside
    private void proceedCalculation(int i, MyArrayList<String> formula) throws Exception {
        calculate(formula.subList(i + 1, formula.lastIndexOf(")")));

        formula.remove(i);
        formula.remove(i + 1);
    }

    //Method for calculating exponent edge cases
    private void proceedExponent(int index, MyArrayList<String> formula) throws Exception {
        //Descent if right operand is open bracket
        if(formula.get(index+1).equals("(")) {
            proceedCalculation(index+1, formula);
            result = 0;
        }

        double leftOperand = getValue(formula.get(index - 1));
        double rightOperand = getValue(formula.get(index + 1));
        result += execute(leftOperand, "^", rightOperand);

        index--;
        updateFormula(formula, index, result);
    }

    //Replace expression with result
    private void updateFormula(MyArrayList<String> formula, int index, double result) {
        //replace left operand with value
        formula.set(index, String.valueOf(result));

        //remove operator & right operand
        formula.remove(index + 1);
        formula.remove(index + 1);
    }

    //Replace expression with result
    private void unaryUpdate(MyArrayList<String> formula, int index, double result) {
        //replace operator with value
        formula.set(index, String.valueOf(result));

        //remove operand
        formula.remove(index + 1);
    }

    //Return last index of exponent in this scope
    private int getLastExpIndex(MyArrayList<String> formula) {
        //Firstly we think than formula does not contains exponent
        int result = -1;

        //If formula contains closing bracket - we will search inside scope of bracket
        int lastExp = formula.contains(")") ? formula.indexOf(")") : formula.size();

        for (int i = 0; i < lastExp; i++) {
            //check if symbol is exponent & its left operand is not bracket
            if (formula.get(i).equals("^") && !formula.get(i-1).equals(")")) {
                result = i;
            }
        }
        return result;
    }

    //Get value of variable or get numeric value of String
    private double getValue(String variable) throws Exception {
        //number processing
        if(variable == null)
            throw new Exception("Number error.");

        if (isNumber(variable))
            return Double.parseDouble(variable);

        //negative variable processing
        if(isNegative(variable)) {
            Double value = variables.get(variable.charAt(1) + "");
            if (value == null)
                throw new Exception("Parsing variable error.");
            return -value;
        }

        //variable processing
        if (variables.get(variable) == null) {
            throw new Exception("Parsing variable error.");
        }

        return variables.get(variable);
    }

    //Execute operation
    private double execute(double leftOperand, String operator, double rightOperand) throws Exception {
        return switch (operator) {
            case "+" -> leftOperand + rightOperand;
            case "-" -> leftOperand - rightOperand;
            case "*" -> leftOperand * rightOperand;
            case "/" -> leftOperand / rightOperand;
            case "^" -> Math.pow(leftOperand, rightOperand);

            default -> throw new Exception("Wrong operand");
        };
    }

    //Unary operators execution
    private double execute(String operator, double operand) throws Exception {
        return switch (operator) {
            case "sin" -> Math.sin(operand);
            case "cos" -> Math.cos(operand);
            case "tan" -> Math.tan(operand);
            case "atan" -> Math.atan(operand);
            case "log10" -> Math.log10(operand);
            case "log2" -> Math.log(operand) / Math.log(2);
            case "sqrt" -> Math.sqrt(operand);

            default -> throw new Exception("Wrong operand");
        };
    }

    //Check if it is number
    private boolean isNumber(String symbol) throws Exception {
        if(symbol == null)
            throw new Exception("Number error!");

        if (isNegative(symbol))
            return Character.isDigit(symbol.charAt(1));
        return Character.isDigit(symbol.charAt(0));
    }

    //Check first symbol of number
    private boolean isNegative(String symbol) {
        if (symbol.length() > 1)
            return symbol.charAt(0) == '-';
        return false;
    }
}
