//REQUIRED CLASS
package project2;

import project1.*;

public class SimplePredicate {
	public String name;
	public ArgumentList arguments;
	
	public SimplePredicate(LexicalAnalyzer lex) throws ParseException {
		Check.tokenType(lex, TokenType.ID);
		name = lex.currentToken().getValue();
		lex.advance();
		
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();
		
		arguments = new ArgumentList(lex);
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
	}
	
	public SimplePredicate(String name, ParameterList parameters) {
		this.name = name;
		arguments = new ArgumentList(parameters);
	}
	
	public String toString() {
		String str = "";
		
		str += name + "(" + arguments.toString() + ")";
		
		return str;
	}
}
