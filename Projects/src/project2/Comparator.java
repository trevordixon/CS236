package project2;

import project1.LexicalAnalyzer;
import project1.Token;
import project1.TokenType;

public class Comparator implements ParameterInterface {
	String name;
	Argument arg1;
	Argument arg2;
	
	public Comparator(LexicalAnalyzer lex) throws ParseException {
		TokenType[] comparatorTypes = {TokenType.LT, TokenType.LE, TokenType.EQ, TokenType.NE, TokenType.GE, TokenType.GT};
		Check.tokenType(lex, comparatorTypes);
		name = lex.currentToken().getValue();
		lex.advance();
		
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();
		
		arg1 = new Argument(lex);

		Check.tokenType(lex, TokenType.COMMA);
		lex.advance();
		
		arg2 = new Argument(lex);
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
	}
	
	public String toString() {
		return name + "(" + arg1.toString() + "," + arg2.toString() + ")";
	}
}
