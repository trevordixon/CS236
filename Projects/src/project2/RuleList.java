//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class RuleList implements Iterable<Rule> {
	private ArrayList<Rule> rules;
	
	public RuleList(LexicalAnalyzer lex) throws ParseException {
		rules = new ArrayList<Rule>();
		
		while (Check.tokenTypeBool(lex, TokenType.ID)) {
			rules.add(new Rule(lex));
		}
	}
	
	public int size() {
		return rules.size();
	}
	
	public String toString() {
		String str = "";
		
		for (Rule rule : rules) {
			str += rule.toString() + "\n";
		}
		
		return str;
	}

	@Override
	public Iterator<Rule> iterator() {
		return rules.listIterator();
	}
}
