package com.company.Tests;
import com.company.Main;
import com.company.Simplification;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ConvertTests extends Assert {
    private static int i = 0;

    private void createInput(String expression) {
        ByteArrayInputStream in = new ByteArrayInputStream(expression.getBytes());
        System.setIn(in);
        System.setIn(System.in);
    }

    private String calculateCallChain(String expressions, String number) {
        String[] exprArray = expressions.split("%>%");
        String value = "";
        for (String exp : exprArray) {
            exp = exp.replaceAll("element", number);

            if (exp.startsWith("map{(") && exp.endsWith(")}")) {
                String mapExp = exp.substring(4, exp.length() - 1);
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

    private String calculation(String filterExpr) {
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

    private String expression(String exprArray) {
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

    private String getNumber(String exprArray, boolean isNegative) {
        StringBuilder number = new StringBuilder();
        if (isNegative) { number.append("-"); i++; }
        while (i < exprArray.length() && Character.isDigit(exprArray.charAt(i))) {
            number.append(exprArray.charAt(i));
            i++;
        }

        return number.toString();
    }

    private String compare(String firstPartResult, String secondPartResult, char sign) {
        firstPartResult = Simplification.simplify(firstPartResult);
        secondPartResult = Simplification.simplify(secondPartResult);
        int firstNumber = Integer.parseInt(firstPartResult);
        int secondNumber = Integer.parseInt(secondPartResult);
        String result = "";

        if (sign == '>') { result = firstNumber > secondNumber ? "1" : "0"; }
        else if (sign == '<') { result = firstNumber < secondNumber ? "1" : "0"; }
        else if (sign == '=') { result = firstNumber == secondNumber ? "1" : "0"; }
        else if (sign == '&') { result = firstNumber != 0 && secondNumber != 0 ? "1" : "0"; }
        else if (sign == '|') { result = firstNumber != 0 || secondNumber != 0 ? "1" : "0"; }

        return result;
    }

    @Test
    public void test1() {
        String standardExpr = "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}";
        createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Main.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};
        List<String> myExprResult = new ArrayList<>();
        List<String> standardExprResult = new ArrayList<>();

        for (String number : numbers) {
            String temp = myExpression;
            String result = calculateCallChain(temp, number);
            if (result != null) { myExprResult.add(result); }

            temp = standardExpr;
            result = calculateCallChain(temp, number);
            if (result != null) { standardExprResult.add(result); }
        }

        assertEquals(myExprResult, standardExprResult);
    }
}
