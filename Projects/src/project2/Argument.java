package project2;

import project1.LexicalAnalyzer;
import project1.Token;
import project1.TokenType;

public class Argument implements ParameterInterface {
	Token argument;
	
	public Argument (LexicalAnalyzer lex) throws ParseException {
		TokenType[] argTypes = {TokenType.ID, TokenType.STRING};
		if (Check.tokenTypeBool(lex, argTypes)) {
			argument = lex.currentToken();
			lex.advance();
		} else {
			throw new ParseException(lex.currentToken());
		}
	}

	public Token getToken() {
		return argument;
	}
	
	public String toString() {
		return argument.getValue();
	}
}
