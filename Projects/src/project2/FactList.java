//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class FactList implements Iterable<Fact> {
	private ArrayList<Fact> facts;
	
	public FactList(LexicalAnalyzer lex) throws ParseException {
		facts = new ArrayList<Fact>();
		
		while (lex.currentToken().getTokenType() == TokenType.ID) {
			facts.add(new Fact(lex));
		}
	}
	
	public int size() {
		return facts.size();
	}
	
	public String toString() {
		String str = "";
		
		for (Fact fact : facts) {
			str += fact.toString();
		}
		
		return str;
	}

	@Override
	public Iterator<Fact> iterator() {
		return facts.iterator();
	}
}
