package main;

import java.util.List;

class Errors {
    static void error(int codeError) {
        switch (codeError) {
            case 1:
                System.out.println("SYNTAX ERROR");
                System.exit(1);
            case 2:
                System.out.println("TYPE ERROR");
                System.exit(2);
        }
    }
}
