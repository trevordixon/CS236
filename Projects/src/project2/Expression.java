package project2;

import project1.LexicalAnalyzer;
import project1.Token;
import project1.TokenType;

public class Expression implements ParameterInterface {
	Parameter param1;
	Token operator;
	Parameter param2;
	
	public Expression(LexicalAnalyzer lex) throws ParseException {
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();

		param1 = new Parameter(lex);
		
		TokenType[] operatorTypes = {TokenType.ADD, TokenType.MULTIPLY};
		Check.tokenType(lex, operatorTypes);
		operator = lex.currentToken();
		lex.advance();
		
		param2 = new Parameter(lex);
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
	}
	
	public String toString() {
		return "(" +
			param1.toString() +
			operator.getValue() +
			param2.toString() +
		")";
	}
}
