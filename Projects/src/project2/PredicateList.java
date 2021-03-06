//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class PredicateList implements Iterable<SimplePredicate> {
	private ArrayList<SimplePredicate> predicates;
	
	public PredicateList(LexicalAnalyzer lex) throws ParseException {
		predicates = new ArrayList<SimplePredicate>();
		predicates.add(new SimplePredicate(lex));
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			predicates.add(new SimplePredicate(lex));
		}
	}
	
	public int size() {
		return predicates.size();
	}
	
	public String toString() {
		String str = "";
		
		for (Iterator<SimplePredicate> predicateIter = predicates.iterator(); predicateIter.hasNext(); ) {
			str += predicateIter.next().toString();
			if (predicateIter.hasNext()) str += ",";
		}
		
		return str;
	}

	@Override
	public Iterator<SimplePredicate> iterator() {
		return predicates.listIterator();
	}
}
