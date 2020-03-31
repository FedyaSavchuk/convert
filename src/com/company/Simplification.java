package com.company;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simplification {
    public static String simplify(String expr) {
        Map<String, Integer> powerMap = new HashMap<>();
        char prevChar = ' ';
        String power = "";
        boolean isNegative = false;

        char sign = '+';
        int value = 0;
        int i = 0;
        while (i < expr.length()) {
            if (Character.isDigit(expr.charAt(i))) {
                int number = getNumber(expr, i, isNegative);
                isNegative = false;

                while (i < expr.length() && Character.isDigit(expr.charAt(i))) { i++; }
                i--;
                value = value == 0 && sign == '*' ? 1 : value;
                value = sign == '*' ? value * number : value + number;
            }
            else if (expr.charAt(i) == '*') { sign = '*'; }
            else if (expr.charAt(i) == '+') { sign = '+'; }
            else if (expr.charAt(i) == 'a') { power = power.isEmpty() ? "a" : power + "*a"; }
            else if (expr.charAt(i) == '-') {
                sign = prevChar == '*' ? '*' : '-';
                isNegative = true;
            }

            if (i == expr.length() - 1 || expr.charAt(i) == '+' || expr.charAt(i) == '-' && sign != '*') {
                if (!power.equals("") && value == 0) { value++; }
                if (powerMap.containsKey(power)) { value += powerMap.get(power); }
                powerMap.put(power, value);
                value = 0;
                power = "";
            }
            prevChar = expr.charAt(i);
            i++;
        }

        return factoring(powerMap);
    }

    private static int getNumber(String expr, int i, boolean isNegative) {
        int start = i;

        while (i < expr.length() && Character.isDigit(expr.charAt(i))) { i++; }
        int number = Integer.parseInt(expr.substring(start, i));
        number = isNegative ? number * -1 : number;

        return number;
    }

    private static String factoring(Map<String, Integer> powerMap) {
        String result = "";
        for (Map.Entry expr : powerMap.entrySet()) {
            if (expr.getKey().equals("") && expr.getValue().equals(0)) { continue; }
            if (!result.isEmpty()) { result += "+"; }
            if (expr.getKey().equals("") || !expr.getValue().equals(1)) {
                result += expr.getValue();
                if (!expr.getKey().equals("")) { result += "*"; };
            }
            if (!expr.getKey().equals("")) { result += expr.getKey(); }
        }
        result = result.replaceAll("\\+-", "-");
        return result;
    }
}
