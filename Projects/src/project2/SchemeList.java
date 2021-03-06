//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class SchemeList implements Iterable<Scheme> {
	private ArrayList<Scheme> schemes;
	
	public SchemeList(LexicalAnalyzer lex) throws ParseException {
		schemes = new ArrayList<Scheme>();
		schemes.add(new Scheme(lex));
		
		while (Check.tokenTypeBool(lex, TokenType.ID)) {
			schemes.add(new Scheme(lex));
		}
	}
	
	public int size() {
		return schemes.size();
	}
	
	public String toString() {
		String str = "";
		
		for (Scheme scheme : schemes) {
			str += scheme.toString();
		}
		
		return str;
	}

	@Override
	public Iterator<Scheme> iterator() {
		return schemes.iterator();
	}
}
