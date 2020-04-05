package com.company.Tests;

import com.company.Simplification;
import com.company.Parentheses;

import java.io.ByteArrayInputStream;
import java.util.List;

class FunctionsForTests {
    private static int i = 0;

    static void createInput(String expression) {
        ByteArrayInputStream in = new ByteArrayInputStream(expression.getBytes());
        System.setIn(in);
        System.setIn(System.in);
    }

    static void applyFeatures(String standardExpr, String myExpression, String[] numbers, List<String> myExprResult, List<String> standardExprResult) {
        for (String number : numbers) {
            String temp = myExpression;
            String result = calculateCallChain(temp, number);
            if (result != null) { myExprResult.add(result); }

            temp = standardExpr;
            result = FunctionsForTests.calculateCallChain(temp, number);
            if (result != null) { standardExprResult.add(result); }
        }
    }

    private static String calculateCallChain(String expressions, String number) {
        String[] exprArray = expressions.split("%>%");
        String value = number;
        for (String exp : exprArray) {
            exp = exp.replaceAll("element", number);

            if (exp.startsWith("map{(") && exp.endsWith(")}")) {
                String mapExp = exp.substring(4, exp.length() - 1);
                if (mapExp.contains("((")) {
                    mapExp = Parentheses.parentheses(mapExp, "arithmetic");
                }

                value = Simplification.simplify(mapExp);
                number = value;
                i = 0;
            } else if (exp.startsWith("filter{(") && exp.endsWith(")}")) {
                String filterExp = exp.substring(7, exp.length() - 1);
                // TODO compare
                i = 0;
                if (calculation(filterExp).equals("0")) { return null; }
            }
        }
        i = 0;
        return value;
    }

    private static String calculation(String filterExpr) {
        if (filterExpr.charAt(i) == '(') { i++; }
        String firstPartResult = expression(filterExpr);
        if (filterExpr.charAt(i) == ')') { i++; return firstPartResult; }

        char sign = filterExpr.charAt(i);
        i++;
        String secondPartResult = expression(filterExpr);
        if (firstPartResult.equals("")) { firstPartResult = "0"; }
        if (secondPartResult.equals("")) { secondPartResult = "0"; }

        if (filterExpr.charAt(i) == ')') { i++; }
        return compare(firstPartResult, secondPartResult, sign);

    }

    private static String expression(String exprArray) {
        String result = "";

        while (i < exprArray.length()) {
            if (exprArray.charAt(i) == '(') { result += calculation(exprArray); }
            else if (Character.isDigit(exprArray.charAt(i))) { result += getNumber(exprArray, false); }
            else if (exprArray.charAt(i) == '-' && Character.isDigit(exprArray.charAt(i + 1))) {
                result += getNumber(exprArray, true);
            }
            else if (exprArray.charAt(i) == '+' || exprArray.charAt(i) == '-' || exprArray.charAt(i) == '*') {
                result += exprArray.charAt(i);
                i++;
            }
            else { break; }
        }

        return result;
    }

    private static String getNumber(String exprArray, boolean isNegative) {
        StringBuilder number = new StringBuilder();
        if (isNegative) { number.append("-"); i++; }
        while (i < exprArray.length() && Character.isDigit(exprArray.charAt(i))) {
            number.append(exprArray.charAt(i));
            i++;
        }

        return number.toString();
    }

    private static String compare(String firstPartResult, String secondPartResult, char sign) {
        long firstNumber = PolishNotationForTests.polishNotaton(firstPartResult);
        long secondNumber = PolishNotationForTests.polishNotaton(secondPartResult);
        String result = "";

        if (sign == '>') { result = firstNumber > secondNumber ? "1" : "0"; }
        else if (sign == '<') { result = firstNumber < secondNumber ? "1" : "0"; }
        else if (sign == '=') { result = firstNumber == secondNumber ? "1" : "0"; }
        else if (sign == '&') { result = firstNumber != 0 && secondNumber != 0 ? "1" : "0"; }
        else if (sign == '|') { result = firstNumber != 0 || secondNumber != 0 ? "1" : "0"; }

        return result;
    }
}
