package com.company;
import org.junit.*;

import java.util.List;

public class UnitTests {
    @Test
    public void testAdd() {
        List<String> result = Parentheses.parentheses("-5+((2*a)*(2*2+2)*3+5)");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("36*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test1() {
        List<String> result = Parentheses.parentheses("2+2");
        String forSimplify = "";
        for (String part : result) {
            forSimplify += part;
        }

        Assert.assertEquals("4", Simplification.simplify(forSimplify));
    }

    @Test
    public void test2() {
        List<String> result = Parentheses.parentheses("-20-4");
        String forSimplify = "";
        for (String part : result) {
            forSimplify += part;
        }

        Assert.assertEquals("-24", Simplification.simplify(forSimplify));
    }

    @Test
    public void test3() {
        List<String> result = Parentheses.parentheses("a*a+a*a+a*a");
        String forSimplify = "";
        for (String part : result) {
            forSimplify += part;
        }

        Assert.assertEquals("3*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test4() {
        List<String> result = Parentheses.parentheses("a+3*a+(a*a)+a*a");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("4*a+2*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test5() {
        List<String> result = Parentheses.parentheses("(a*a)+(a*a)");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("2*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test6() {
        List<String> result = Parentheses.parentheses("2*-1");
        String forSimplify = "";
        for (String part : result) {
            forSimplify += part;
        }

        Assert.assertEquals("-2", Simplification.simplify(forSimplify));
    }

    @Test
    public void test7() {
        List<String> result = Parentheses.parentheses("2-2");
        String forSimplify = "";
        for (String part : result) {
            forSimplify += part;
        }

        Assert.assertEquals("", Simplification.simplify(forSimplify));
    }

    @Test
    public void test8() {
        List<String> result = Parentheses.parentheses("6*a*(2-1)*(2+2)");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("24*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test9() {
        List<String> result = Parentheses.parentheses("a*a*(2+3)*a");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("5*a*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test10() {
        List<String> result = Parentheses.parentheses("a*a*(7+(2+(3+2)*3)*a)+2");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("2+17*a*a*a+7*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test11() {
        List<String> result = Parentheses.parentheses("2+((3+2)*3)*a");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("2+15*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test12() {
        List<String> result = Parentheses.parentheses("a*a*7+(2+(3+2)*3)*a");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("17*a+7*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test13() {
        List<String> result = Parentheses.parentheses("a*a*7+(2+(3+2)*3)*a+3-(2*2)");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("-1+17*a+7*a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test14() {
        List<String> result = Parentheses.parentheses("((2+2))");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("4", Simplification.simplify(forSimplify));
    }

    @Test
    public void test15() {
        List<String> result = Parentheses.parentheses("(2*(2+2))");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("8", Simplification.simplify(forSimplify));
    }

    @Test
    public void test16() {
        List<String> result = Parentheses.parentheses("a*a+2+a*(a*a+4)+1");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("3+4*a+a*a*a+a*a", Simplification.simplify(forSimplify));
    }

    @Test
    public void test17() {
        List<String> result = Parentheses.parentheses("-5-(3*2)");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("-11", Simplification.simplify(forSimplify));
    }

    @Test
    public void test18() {
        List<String> result = Parentheses.parentheses("-5-(3*2)*2*a*2");
        String forSimplify = "";
        for (String part : result) {
            if (part.startsWith("+") || part.startsWith("-") || part.startsWith("*")) {
                forSimplify += part;
            }
            else {
                forSimplify += "+" + part;
            }
        }

        Assert.assertEquals("-5-24*a", Simplification.simplify(forSimplify));
    }
}
