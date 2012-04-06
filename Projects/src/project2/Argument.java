package project2;

import project1.LexicalAnalyzer;
import project1.Token;
import project1.TokenType;

public class Argument implements ParameterInterface, Comparable<Argument> {
	Token argument;
	
	public Argument(LexicalAnalyzer lex) throws ParseException {
		TokenType[] argTypes = {TokenType.ID, TokenType.STRING};
		if (Check.tokenTypeBool(lex, argTypes)) {
			argument = lex.currentToken();
			lex.advance();
		} else {
			throw new ParseException(lex.currentToken());
		}
	}

	public Argument(Token argument) {
		this.argument = argument;
	}
	
	public Token getToken() {
		return argument;
	}
	
	public String toString() {
		return argument.getValue();
	}
	
	public TokenType getType() {
		return argument.getTokenType();
	}
	
	public String getValue() {
		return argument.getValue();
	}

	@Override
	public int compareTo(Argument a) {
		return argument.getValue().compareTo(a.getValue());
	}
	
	public boolean equals(Argument a) {
		return a.argument.equals(this.argument);
	}
}
