package tests;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionsTests extends Assert {

    private String getOutput(String[] expressions) {
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        main.Convertor.convert(expressions);

        return baOut.toString();
    }

    @Test
    public void test1() {
        String startExpr = "map{(element+10)}%>%filte{(element>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }

    @Test
    public void test2() {
        String startExpr = "map(element+10)}%>%filte{(element>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }

    @Test
    public void test3() {
        String startExpr = "map{((element+10)}%>%filter{(element>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }

    @Test
    public void test4() {
        String startExpr = "map{((element+10)}%%filter{(element>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }

    @Test
    public void test5() {

        String startExpr = "map{((element+10))}%>%filter{(element>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }

    @Test
    public void test6() {
        String startExpr = "map{((x+10))}%>%filter{(x>10)}";
        FunctionsForTests.createInput(startExpr);
        String[] expressions = startExpr.split("%>%");

        String myProgramOut = getOutput(expressions);
        assertEquals(myProgramOut, "SYNTAX ERROR\n");
    }
}
