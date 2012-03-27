package project2;

import project1.Token;

public class ParseException extends Exception {
	private Token token;
	
	public ParseException(Token token) {
		this.setToken(token);
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Token getToken() {
		return token;
	}
}
