package com.company;

import java.util.*;

class Parentheses {
    static List<String> parentheses(String expr) {
        List<String> result = new ArrayList<>();
        List<String> bracketsResult = new ArrayList<>();

        int i = 0;
        char lastSign = '+';
        String tempString = "";

        while (i < expr.length()) {
            if (expr.charAt(i) == '*') { lastSign = '*'; }
            else if (expr.charAt(i) == '+' || i > 0 && expr.charAt(i) == '-' && expr.charAt(i - 1) != '*') {
                if (i > 0 && expr.charAt(i - 1) == '-') { tempString = ""; i++; continue; }
                if (!bracketsResult.isEmpty() && lastSign == '*') { result.addAll(multBrackets(bracketsResult, tempString)); }
                else if (!bracketsResult.isEmpty()) { result.addAll(bracketsResult); }
                else if (!tempString.isEmpty() && !tempString.equals("+") && !tempString.equals("-")) { result.add(tempString); }

                bracketsResult.clear();
                tempString = "";
                lastSign = expr.charAt(i);
            }
            else if (expr.charAt(i) == '(') {
                int start = i;
                i = endBracketsSearch(expr, i);
                String insideBrackets = expr.substring(start + 1, i);
                bracketsResult = openBrackets(bracketsResult, insideBrackets, lastSign, tempString);

                if (!tempString.isEmpty() && !tempString.equals("+") && !tempString.equals("-") && lastSign != '*') { result.add(tempString); }
                tempString = "";
            }

            if (i < expr.length() && expr.charAt(i) != ')') { tempString += expr.charAt(i); }
            i++;
        }
        if (!bracketsResult.isEmpty() && lastSign == '*') { result.addAll(multBrackets(bracketsResult, tempString)); }
        else if (!bracketsResult.isEmpty()) { result.addAll(bracketsResult); }
        else if (!tempString.isEmpty() && !tempString.equals("+") && !tempString.equals("-")) { result.add(tempString); }

        return result;
    }

    private static List<String> openBrackets(List<String> bracketsResult, String insideBrackets, char lastSign, String tempString) {
        if (!bracketsResult.isEmpty() && lastSign == '*') {
            List<String> secondBrackets = parentheses(insideBrackets);
            bracketsResult = multTwoBrackets(secondBrackets, bracketsResult);
        }
        else { bracketsResult = parentheses(insideBrackets); }

        if (lastSign == '*') { bracketsResult = multBrackets(bracketsResult, tempString); }
        else if (lastSign == '-') { bracketsResult = openNegativeBrackets(bracketsResult); }

        return bracketsResult;
    }


    private static List<String> openNegativeBrackets(List<String> negativeExpr) {
        List<String> bracketsResult = new ArrayList<>();

        for (String expr : negativeExpr) {
            if (expr.startsWith("+-")) { bracketsResult.add(expr.substring(2)); }
            else if (expr.startsWith("-")) { bracketsResult.add("+" + expr.substring(1)); }
            else if (expr.startsWith("+")) { bracketsResult.add("-" + expr.substring(1)); }
            else { bracketsResult.add("-" + expr); }
        }

        return bracketsResult;
    }


    private static int endBracketsSearch(String expr, int i) {
        Stack<Character> brackets = new Stack<>();
        brackets.add('(');

        while (!brackets.isEmpty()) {
            i++;
            if (expr.charAt(i) == '(') { brackets.add('('); }
            else if (expr.charAt(i) == ')') {
                if (brackets.peek() == '(') { brackets.pop(); }
                else { System.out.println("ERROR"); }
            }
        }

        return i;
    }


    private static List<String> multBrackets(List<String> elem, String expr) {
        List<String> temp = new ArrayList<>();
        if (expr.endsWith("*")) { expr = expr.substring(0, expr.length() - 1); }
        else if (expr.startsWith("*")) { expr = expr.substring(1); }
        if (expr.isEmpty()) { return elem; }

        for (String i : elem) {
            if (expr.startsWith("+")) { expr = expr.substring(1); }
            temp.add(i + "*" + expr);
        }

        return temp;
    }


    private static List<String> multTwoBrackets(List<String> firstBrackets, List<String> secondBrackets) {
        List<String> temp = new ArrayList<>();
        if (secondBrackets.isEmpty()) {
            temp.addAll(firstBrackets);
        }
        for (String i : firstBrackets) {
            for (String j : secondBrackets) {
                if (j.startsWith("+")) { j = j.substring(1); }
                temp.add(i + "*" + j);
            }
        }

        return temp;
    }


    // TODO Use ENUM for mapping codeError and ErrorMessage
    private static int checkErrors(char prevChar, char presentChar) {
        Boolean prevCharIsOperation = prevChar == '*' || prevChar == '-' || prevChar == '+';
        Boolean presentCharIsOperation = presentChar == '*' || presentChar == '+' || presentChar == '-';

        if (Character.isDigit(prevChar) && presentChar == 'a') {
            System.out.println("Error!\nUse '*' between '" + prevChar + "' and '" + presentChar + "'");
        } else if (Character.isDigit(presentChar) && prevChar == 'a') {
            System.out.println("Error!\nUse '*' between '" + presentChar + "' and '" + prevChar + "'");
        } else if (prevCharIsOperation && presentCharIsOperation) {
            System.out.println("Error!\nUse number between two operation '" + presentChar + "' and '" + prevChar + "'");
        } else if (!Character.isDigit(presentChar) && !presentCharIsOperation && presentChar != 'a') {
            System.out.println("Error!\nNon Valid symbol '" + presentChar + "'");
        } else {
            return 0;
        }
        return 1;
    }
}
