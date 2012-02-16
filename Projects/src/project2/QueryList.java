//REQUIRED CLASS
package project2;

import java.util.ArrayList;

import project1.*;

public class QueryList {
	private ArrayList<SimplePredicate> queries;
	
	public QueryList(LexicalAnalyzer lex) throws ParseException {
		queries = new ArrayList<SimplePredicate>();
		
		do {
			queries.add(new SimplePredicate(lex));
			Check.tokenType(lex, TokenType.Q_MARK);
			lex.advance();
		} while (Check.tokenTypeBool(lex, TokenType.ID));
	}
	
	public int size() {
		return queries.size();
	}
	
	public String toString() {
		String str = "";
		
		for (SimplePredicate query: queries) {
			str += "  " + query.toString() + "?\n";
		}
		
		return str;
	}
}
