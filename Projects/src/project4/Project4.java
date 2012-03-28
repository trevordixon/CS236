//REQUIRED CLASS - KEEP THIS FILE IN THE 'project4' DIRECTORY WITH 'package project4'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import project1.LexicalAnalyzer;
import project2.DatalogProgram;
import project2.ParseException;

public class Project4
{
	public static String body(String[] args)
	{
		LexicalAnalyzer lex = new LexicalAnalyzer();
		DatalogProgram datalogProgram;

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
			datalogProgram = new DatalogProgram(lex);
		} catch (ParseException e) {
			return "Failure!\n  " + e.getToken().toString();
		}

		return new Database(datalogProgram).toString();
	}

	public static void main(String[] args) {
		String[] _args = {"/home/tdixon/Documents/CS236/examples/ex42.txt"};
		System.out.print(body(_args));
	}
}
