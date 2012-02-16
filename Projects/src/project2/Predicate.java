//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class Predicate {
	String name;
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
}
