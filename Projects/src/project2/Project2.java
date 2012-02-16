//REQUIRED CLASS - KEEP THIS FILE IN THE 'project2' DIRECTORY WITH 'package project2'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'DatalogProgram' and return an ouput string
//DO NOT ADD METHODS

package project2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import project1.LexicalAnalyzer;
import project1.TokenType;

public class Project2
{
	public static void main(String[] args) {
		String[] _args = {"/home/tdixon/Documents/CS236/examples/ex22.txt"};
		System.out.print(body(_args));
	}
	
	public static String body(String[] args) {
		LexicalAnalyzer lex = new LexicalAnalyzer();
		String str = "";

		try {
            BufferedReader in = null;
            if (args.length == 0) {
                in = new BufferedReader(new InputStreamReader(System.in));
            } else {
                in = new BufferedReader(new FileReader(args[0]));
            }
            
            lex.tokenize(in);
        } catch (IOException e) {
            System.out.println("IO Error");
        }
		
		try {
			DatalogProgram datalogProgram = new DatalogProgram(lex);
			str += "Success!\n";
			str += datalogProgram.toString();
		} catch (ParseException e) {
			str += "Failure!\n  " + e.token.toString();
		}
		
		return str;
	}
}
