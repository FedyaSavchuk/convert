package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Convertor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] expressions = scanner.nextLine().split("%>%");

        convert(expressions);
    }


    public static String convert(String[] expressions) {
	    String map = "element";
	    String filter = "";
	    List<String> filterList = new ArrayList<>();

	    for (String exp : expressions) {
	        if (exp.startsWith("map{(") && exp.endsWith(")}")) {
                String mapExp = exp.substring(4, exp.length() - 1);

                if (map.equals("element")) {
	                map = mapExp;
                } else {
                    mapExp = mapExp.replaceAll("element", map);
                    map = mapExp;
                }
            }

	        else if (exp.startsWith("filter{(") && exp.endsWith(")}")) {
                String filterExp = exp.substring(7, exp.length() - 1);
                if (!map.equals("element")) {
                    String temp = Parentheses.parentheses(map, "arithmetic");
                    if (temp == null) { return null; }
                    filterExp = filterExp.replaceAll("element", Simplification.simplify(temp));
                }

	            if (filter.isEmpty() && map.isEmpty()) {
	                filter = exp;
                    filterList.add(filter);
                }
	            else if (filter.isEmpty()) {
                    filterList.add(filterExp);
                }
	            else {
	                filterList.add(filter);
                }
            }

	        else {
                Errors.error(1);
                return null;
            }
        }

	    filter = "";
	    if (filterList.size() > 1) { filter += "("; }
	    for (String filt : filterList) {
	        if (filter.equals("(") || filter.equals("")) {
	            filter += filt;
            } else {
                filter += "&" + filt;
            }
        }
        if (filterList.size() > 1) { filter += ")"; }

        if (!map.equals("element")) {
            map = Parentheses.parentheses(map, "arithmetic");
            if (map == null) { return null; }
            map = Simplification.simplify(map);
        }

	    String result = "filter{" + filter + "}%>%map{(" + map + ")}";
        System.out.println(result);
        return result;
    }
}
