//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class PredicateList {
	private ArrayList<Predicate> predicates;
	
	public PredicateList(LexicalAnalyzer lex) throws ParseException {
		predicates = new ArrayList<Predicate>();
		predicates.add(new Predicate(lex));
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			predicates.add(new Predicate(lex));
		}
	}
	
	public int size() {
		return predicates.size();
	}
	
	public String toString() {
		String str = "";
		
		for (Iterator<Predicate> predicateIter = predicates.iterator(); predicateIter.hasNext(); ) {
			str += predicateIter.next().toString();
			if (predicateIter.hasNext()) str += ",";
		}
		
		return str;
	}
}
