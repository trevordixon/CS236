//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an ouput string
//DO NOT ADD METHODS

package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project1 {
    public static void main(String[] args) {
        System.out.print(body(args));
    }

    public static String body(String[] args) {
        LexicalAnalyzer lexer = new LexicalAnalyzer();

        try {
            BufferedReader in = null;
            if (args.length == 0) {
                in = new BufferedReader(new InputStreamReader(System.in));
            } else {
                in = new BufferedReader(new FileReader(args[0]));
            }
            lexer.tokenize(in);
        } catch (IOException e) {
            return "IO Error";
        }

        return lexer.toString();
    }
}
