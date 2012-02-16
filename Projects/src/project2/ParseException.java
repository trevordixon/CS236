package project2;

import project1.Token;

public class ParseException extends Exception {
	Token token;
	
	public ParseException(Token token) {
		this.token = token;
	}
}
