//REQUIRED CLASS
package project2;

import project1.*;

public class Predicate {
	public String name;
	ParameterList parameters;
	
	public Predicate(LexicalAnalyzer lex) throws ParseException {
		Check.tokenType(lex, TokenType.ID);
		name = lex.currentToken().getValue();
		lex.advance();
		
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();
		
		parameters = new ParameterList(lex);
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
	}
	
	public String toString() {
		String str = "";
		
		str += name + "(" + parameters.toString() + ")";
		
		return str;
	}
	
	public Query toQuery() {
		return new Query(name, parameters);
	}
}
