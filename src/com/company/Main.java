package com.company;
import java.util.List;
import java.util.Scanner;

public class Main extends Test {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    String[] expressions = scanner.nextLine().split("%>%");

        System.out.println(Test.parenthesesTest("", "arithmetic"));
	    String map = "";
	    String filter = "";

	    for (String exp : expressions) {

	        if (exp.startsWith("map{(") && exp.endsWith(")}")) {
                String mapExp = exp.substring(4, exp.length() - 1);

                if (map.isEmpty()) {
	                map = mapExp;
                } else {
                    mapExp = mapExp.replaceAll("element", map);
                    map = Parentheses.initialization(mapExp);
                }
            }

	        else if (exp.startsWith("filter") && exp.endsWith(")}")) {
                String filterExp = exp.substring(7, exp.length() - 1);
                filterExp = filterExp.replaceAll("element", map);

	            if (filter.isEmpty() && map.isEmpty()) {
	                filter = exp;
                }
	            else if (filter.isEmpty()) {
                    filter = filterExp;
                }
	            else {
	                filter = filter + "&" + filterExp;
                }
            }

	        else {
                System.out.println("TYPE ERROR");
                return;
            }
        }

        System.out.println("map{" + map + "}");
        System.out.println("filter{" + filter + "}");
    }
}
