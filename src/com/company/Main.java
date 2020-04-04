package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] expressions = scanner.nextLine().split("%>%");

        convert(expressions);
    }


    public static String convert(String[] expressions) {
	    String map = "";
	    String filter = "";

	    for (String exp : expressions) {
	        if (exp.startsWith("map{(") && exp.endsWith(")}")) {
                String mapExp = exp.substring(4, exp.length() - 1);

                if (map.isEmpty()) {
	                map = mapExp;
                } else {

                    mapExp = mapExp.replaceAll("element", map);
                    map = Parentheses.parentheses(mapExp, "arithmetic");
                    map = Simplification.simplify(map);
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
                System.out.println("SYNTAX ERROR");
                return "SYNTAX ERROR";
            }
        }
	    String result = "filter{" + filter + "}%>%map{(" + map + ")}";
        System.out.println(result);
        return result;
    }
}
