package tests;

import java.util.Deque;
import java.util.LinkedList;

public class PolishNotationForTests {

    static long polishNotaton(String expr) {
        Deque<Long> operators = new LinkedList<>();
        Deque<Character> operations = new LinkedList<>();
        long number = 0;
        char lastSign = '+';
        expr = expr.replaceAll("\\+-", "-");
        expr = expr.replaceAll("-\\+", "-");
        expr = expr.replaceAll("--", "+");
        char[] elem = expr.toCharArray();
        char lastElem = elem[0];


        for (int i = 0; i < elem.length; i++) {
            if (Character.isDigit(elem[i]) || i == 0 && elem[i] == '-' || ((elem[i] == '-' && (lastElem == '+' || lastElem == '*' || lastElem == '+')))) {
                if (elem[i] == '-' && (i == 0 || lastElem == '+' || lastElem == '*' || lastElem == '-')) {
                    i++;
                    number = getNumber(elem, i, true);
                }
                else { number = getNumber(elem, i, false); }
                while (i < elem.length && Character.isDigit(elem[i])) { i++; }
                operators.add(number);
                i--;
            }
            else {
                long oper = 0;
                if (lastSign == '*') {
                    oper = operators.pollLast();
                    operations.pollLast();
                    operators.add(operators.pollLast() * oper);
                }
                else if (lastElem == '+') {
                    oper = operators.pollLast();
                    operations.pollLast(); operators.add(operators.pollLast() + oper);
                }
                else if (lastElem == '-') {
                    oper = operators.pollLast();
                    operations.pollLast(); operators.add(operators.pollLast() - oper);
                }
                operations.add(elem[i]);
                lastSign = elem[i];
            }
            if (i < elem.length) {lastElem = elem[i];}
        }

        if (operations.isEmpty()) {
            return operators.pop();
        }

        while (!operations.isEmpty() && operations.peekLast() == '*') {
            long oper = 0;
            if (operations.peekLast() == '-') { oper = operators.pollLast(); operators.add(operators.pollLast() - oper); }
            else if (operations.peekLast() == '+') { oper = operators.pollLast(); operators.add(operators.pollLast() + oper); }
            else if (operations.peekLast() == '*') { oper = operators.pollLast(); operators.add(operators.pollLast() * oper); }
            operations.pollLast();
        }

        while (!operations.isEmpty()) {
            if (operations.peekFirst() == '-') { operators.addFirst(operators.pollFirst() - operators.pollFirst()); }
            else if (operations.peekFirst() == '+') { operators.addFirst(operators.pollFirst() + operators.pollFirst()); }
            else if (operations.peekFirst() == '*') { operators.addFirst(operators.pollFirst() * operators.pollFirst()); }
            operations.pop();
        }

        return operators.pop();
    }

    private static int getNumber(char[] elem, int i, boolean isNegative) {
        int number = 0;
        while (i < elem.length && Character.isDigit(elem[i])) {
            number = number * 10 + Character.getNumericValue(elem[i]);
            i++;
        }
        if (isNegative) { number *= -1; }

        return number;
    }


}
