package com.company;
import java.util.HashMap;
import java.util.Map;

public class Simplification {
    public static String simplify(String expr) {
        Map<String, Integer> powerMap = new HashMap<>();
        expr = expr.replaceAll("element", "a");
        expr = expr.replaceAll("[()]", "");
        expr = expr.replaceAll("\\*\\+", "*");
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
            else if (expr.charAt(i) == 'a') {
                value = power.isEmpty() && value == 0 ? 1 : value;
                if (isNegative) { value = value * -1; isNegative = false; }
                power = power.isEmpty() ? "a" : power + "*a";
            }
            else if (expr.charAt(i) == '-') {
                isNegative = prevChar != '-';
                sign = prevChar == '*' ? '*' : '-';
            }

            if (i == expr.length() - 1 || expr.charAt(i) == '+' || expr.charAt(i) == '-' && sign != '*') {
                if (powerMap.containsKey(power)) { value += powerMap.get(power); }
                powerMap.put(power, value);
                value = 0;
                power = "";
            }

            prevChar = expr.charAt(i);
            i++;
        }

        String result = factoring(powerMap);
        result = result.replaceAll("a", "element");
        return result;
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
            if (!expr.getKey().equals("") && expr.getValue().equals(-1)) { result += "-"; }
            else if (!result.isEmpty()) { result += "+"; }
            if (expr.getKey().equals("") || !expr.getValue().equals(1) && !expr.getValue().equals(0) && !expr.getValue().equals(-1)) {
                result += expr.getValue();
                if (!expr.getKey().equals("")) { result += "*"; };
            }
            if (!expr.getKey().equals("") && !expr.getValue().equals(0)) { result += expr.getKey(); }
        }
        result = result.replaceAll("\\+-", "-");
        return result;
    }
}
