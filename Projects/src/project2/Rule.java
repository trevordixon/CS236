//REQUIRED CLASS
package project2;

import project1.*;

public class Rule {
	SimplePredicate simplePredicate;
	PredicateList predicateList;
	
	public Rule(LexicalAnalyzer lex) throws ParseException {
		simplePredicate = new SimplePredicate(lex);
		
		Check.tokenType(lex, TokenType.COLON_DASH);
		lex.advance();
		
		predicateList = new PredicateList(lex);

		Check.tokenType(lex, TokenType.PERIOD);
		lex.advance();
	}
	
	public String toString() {
		String str = "";
		
		str += "  " + simplePredicate.toString() + " :- " + predicateList.toString() + ".";
		
		return str;
	}
}
