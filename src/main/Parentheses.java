package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parentheses {
    static private int i = 0;

    public static String parentheses(String expression, String type) {
        List<Character> validOperations = new ArrayList<>();
        if (type.equals("boolean")) { validOperations = Arrays.asList('>', '<', '=', '&', '|'); }
        else if (type.equals("arithmetic")) { validOperations = Arrays.asList('+', '-', '*'); }

        expression = expression.replaceAll("element", "a");
        expression = expression.replaceAll(" ", "");

        List<String> result;
        char[] exprArray = expression.toCharArray();
        result = conveer(exprArray, validOperations);

        if (result == null) { i = 0; return null; }
        if (i != expression.length()) { Errors.error(1); i = 0; return null; }
        i = 0;

        return String.join("", result).replaceAll("a", "element");
    }


    private static List<String> conveer(char[] exprArray, List<Character> validOperations) {
        List<Character> boolOperations = Arrays.asList('>', '<', '=', '&', '|');
        List<Character> arithmeticOperations = Arrays.asList('+', '-', '*');
        List<String> firstPartResult;
        List<String> secondPartResult;
        Stack<Character> brackets = new Stack<>();

        if (exprArray[i] == '(') { i++; brackets.add('('); }
        else Errors.error(1);

        firstPartResult = expression(exprArray, validOperations);
        if (i >= exprArray.length || firstPartResult == null) { i = 0; Errors.error(1); }
        char sign = exprArray[i];
        if (!boolOperations.contains(sign) && !arithmeticOperations.contains(sign)) { i = 0; Errors.error(1); }
        else if (!validOperations.contains(sign)) { i = 0; Errors.error(2); }
        i++;
        secondPartResult = expression(exprArray, validOperations);

        if (exprArray[i] == ')') { i++; brackets.pop(); }
        if (!brackets.isEmpty() || secondPartResult == null) { i = 0; Errors.error(1); }

        return calculation(firstPartResult, secondPartResult, sign);
    }


    private static List<String> expression(char[] exprArray, List<Character> validOperations) {
        List<String> result = new ArrayList<>();
        if (exprArray[i] == 'a') { result.add("a"); i++; }
        else if (exprArray[i] == '(') {
            List<String> bracketsResult = conveer(exprArray, validOperations);
            if (bracketsResult == null) { i = 0; return null; }
            result.addAll(bracketsResult);
        }
        else if (Character.isDigit(exprArray[i])) { result.add(getNumber(exprArray, false)); }
        else if (exprArray[i] == '-' && Character.isDigit(exprArray[i + 1])) {
            result.add(getNumber(exprArray, true));
        }
        else { return null; }

        return result;
    }


    private static List<String> calculation(List<String> firstPartResult, List<String> secondPartResult, char sign) {
        List<String> temp = new ArrayList<>();
        if (sign == '*') { temp = multiplication(firstPartResult, secondPartResult); }
        else if (sign == '+') { temp = addition(firstPartResult, secondPartResult); }
        else if (sign == '-') { temp = subtract(firstPartResult, secondPartResult); }

        return temp;
    }


    private static List<String> multiplication(List<String> firstPartResult, List<String> secondPartResult) {
        List<String> temp = new ArrayList<>();
        for (String i : firstPartResult) {
            for (String j : secondPartResult) {
                if (i.startsWith("+") && j.startsWith("+")) {
                    temp.add(j + "*" + i.substring(1));
                } else if (j.startsWith("+") || j.startsWith("-")) {
                    temp.add(j + "*" + i);
                } else {
                    temp.add(i + "*" + j);
                }
            }
        }

        return temp;
    }


    private static List<String> subtract(List<String> firstPartResult, List<String> secondPartResult) {
        List<String> temp = new ArrayList<>(firstPartResult);

        for (String exprPart : secondPartResult) {
            if (exprPart.startsWith("+-")) { temp.add(exprPart.substring(2)); }
            else if (exprPart.startsWith("-")) { temp.add("+" + exprPart.substring(1)); }
            else if (exprPart.startsWith("+")) { temp.add("-" + exprPart.substring(1)); }
            else { temp.add("-" + exprPart); }
        }

        return temp;
    }


    private static List<String> addition(List<String> firstPartResult, List<String> secondPartResult) {
        List<String> temp = new ArrayList<>(firstPartResult);

        for (String exprPart : secondPartResult) {
            if (!exprPart.startsWith("-") && !exprPart.startsWith("+")) { temp.add("+" + exprPart); }
            else { temp.add(exprPart); }
        }

        return temp;
    }


    private static String getNumber(char[] exprArray, boolean isNegative) {
        StringBuilder number = new StringBuilder();

        if (isNegative) { number.append("-"); i++; }
        while (i < exprArray.length && Character.isDigit(exprArray[i])) {
            number.append(exprArray[i]);
            i++;
        }

        return number.toString();
    }
}
