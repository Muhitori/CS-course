package com.shpp.p2p.cs.kturevich.assignment17.assignment11;

import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;

import java.util.ArrayList;

/**
 * Class to create ArrayList from formula string
 * */
public class Tokenizer {
    private MyArrayList<String> formulaList = new MyArrayList<>();

    Tokenizer(String formula) {
        parse(formula);
    }

    public MyArrayList<String> getFormulaList() {
        return formulaList;
    }

    private void parse(String formula){
        String buffer = "";
        boolean isOperand;
        char previousSymbol = formula.charAt(0);

        for (int i = 0; i < formula.length(); i++) {
            char currentSymbol = formula.charAt(i);
            isOperand = true;

            //If it is operator add to list what is in buffer & operator
            //if buffer is not empty - refresh
            if (validateOperator(previousSymbol, currentSymbol)) {
                isOperand = false;

                if (buffer.length() > 0) {
                    formulaList.add(buffer);
                    buffer = "";
                }
                formulaList.add(String.valueOf(currentSymbol));
            }

            if (isOperand) {
                buffer += formula.charAt(i);
            }

            if (i == formula.length() - 1) {
                formulaList.add(buffer);
            }

            previousSymbol = currentSymbol;
        }

        for (String s : formulaList) {
            if (s.equals("")) {
                formulaList.remove(formulaList.indexOf(s));
            }
        }
    }

    //Return tru if iy is operator
    private boolean validateOperator(char previousSymbol, char currentSymbol) {
        //Edge cases for minus
        if (currentSymbol == '-' && !isNumber(previousSymbol) && previousSymbol != ')') {
            return false;
        } else {
            return isOperator(currentSymbol) || isBracket(currentSymbol);
        }
    }

    private boolean isBracket(char c) {
        return '(' == c || ')' == c;
    }

    private boolean isOperator(char c) {
        String s = String.valueOf(c);
        String operators = "+-*/^";
        return operators.contains(s);
    }

    private boolean isNumber(char c) {
        return Character.isLetterOrDigit(c);
    }
}
