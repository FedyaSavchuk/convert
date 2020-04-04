package com.company;
import java.util.*;

class Parentheses {
    static private int i = 0;

    static String parentheses(String expression, String type) {
        List<Character> validOperations = new ArrayList<>();
        if (type.equals("boolean")) { validOperations = Arrays.asList('>', '<', '=', '&', '|'); }
        else if (type.equals("arithmetic")) { validOperations = Arrays.asList('+', '-', '*'); }

        expression = expression.replaceAll("element", "a");
        expression = expression.replaceAll(" ", "");

        List<String> result;
        char[] exprArray = expression.toCharArray();
        result = conveer(exprArray, validOperations);

        if (result == null) { return null; }
        if (i != expression.length()) { error(1); return null; }
        i = 0;

        return String.join("", result).replaceAll("a", "element");
    }

    private static List<String> conveer(char[] exprArray, List<Character> validOperations) {
        List<String> firstPartResult;
        List<String> secondPartResult;

        if (exprArray[i] == '(') { i++; }
        else return error(1);

        firstPartResult = expression(exprArray, validOperations);
        char sign = exprArray[i];
        if (!validOperations.contains(sign)) { return error(2); }
        i++;
        secondPartResult = expression(exprArray, validOperations);

        if (exprArray[i] != ')') { return error(1); }
        i++;

        return calculation(firstPartResult, secondPartResult, sign);
    }

    private static List<String> expression(char[] exprArray, List<Character> validOperations) {
        List<String> result = new ArrayList<>();
        if (exprArray[i] == 'a') { result.add("a"); i++; }
        else if (exprArray[i] == '(') { result.addAll(conveer(exprArray, validOperations)); }
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

    private static List<String> error(int codeError) {
        switch (codeError) {
            case 1:
                System.out.println("SYNTAX ERROR");
            case 2:
                System.out.println("TYPE ERROR");
        }
        return null;
    }
}
