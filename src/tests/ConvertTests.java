package tests;
import main.Convertor;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class ConvertTests extends Assert {
    private List<String> myExprResult = new ArrayList<>();
    private List<String> standardExprResult = new ArrayList<>();

    @Test
    public void test1() {
        String standardExpr = "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = main.Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test2() {
        String standardExpr = "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}%>%map{(element+10)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test3() {
        String standardExpr = "map{(element+10)}%>%filter{(element>10)}%>%filter{(element>100)}%>%map{(element*element)}%>%map{(element+10)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test4() {
        String standardExpr = "map{(element-(element*element))}%>%filter{(element>(10<2))}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test5() {
        String standardExpr = "map{((element*element)*(element-5))}%>%filter{(element>(10<(element|element)))}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test6() {
        String standardExpr = "map{((element*element)*(element-(5+element)))}%>%filter{(element>(10<(element|element)))}%>%map{(element*element)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test7() {
        String standardExpr = "map{((element*element)*(element-(5+element)))}%>%filter{(element<(10&(element|element)))}%>%map{(element*element)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test8() {
        String standardExpr = "map{((element*(element+3))*(element-(5+element)))}%>%filter{(element<(10&(element|element)))}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test9() {
        PolishNotationForTests.polishNotaton("10-15*20+-5*20*20");
        String standardExpr = "map{((element*(element+3))*(element-(5+element)))}%>%filter{(element<(10&(element|element)))}%>%map{(element+10)}%>%filter{(element>5)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }


    @Test
    public void test10() {
        String standardExpr = "map{(5*element)}%>%filter{(element<(10&(element|element)))}%>%map{(element+10)}%>%filter{(element>5)}" +
                "%>%map{((element*(element+3))*(element-(5+element)))}%>%filter{(element<(10&(element|element)))}%>%map{(element+10)}%>%filter{(element>5)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test11() {
        String standardExpr = "filter{(element>10)}%>%filter{(element<20)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test12() {
        String standardExpr = "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

    @Test
    public void test13() {
        String standardExpr = "map{(5*element)}%>%filter{(element<(10&(element|element)))}%>%map{(element+10)}%>%filter{(element>5)}" +
                "%>%map{((element*(element+3))*(element-(5+element)))}%>%filter{(element<(10&(element|element)))}%>%map{(element+10)}%>%filter{(element>5)}" +
                "%>%filter{(element>5)}%>%map{(element*(element*-1))}%>%filter{(element<(10&(element|element)))}%>%map{(element-(10+5))}";
        FunctionsForTests.createInput(standardExpr);
        String[] expressions = standardExpr.split("%>%");
        String myExpression = Convertor.convert(expressions);
        String[] numbers = {"0", "-5", "20", "1", "264", "34", "-24", "0", "284", "-638"};

        FunctionsForTests.applyFeatures(standardExpr, myExpression, numbers, myExprResult, standardExprResult);
        assertEquals(myExprResult, standardExprResult);
    }

}
