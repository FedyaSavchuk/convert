package main;

import java.util.List;

class Errors {
    static List<String> error(int codeError) {
        switch (codeError) {
            case 1:
                System.out.println("SYNTAX ERROR");
            case 2:
                System.out.println("TYPE ERROR");
        }
        return null;
    }
}
