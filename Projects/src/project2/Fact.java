//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class Fact {
	public String name;
	public ArrayList<Token> facts;
	
	public Fact(String name, ArrayList<Token> facts) {
		this.name = name;
		this.facts = facts;
	}
	
	public Fact(LexicalAnalyzer lex) throws ParseException {
		facts = new ArrayList<Token>();
		
		Check.tokenType(lex, TokenType.ID);
		name = lex.currentToken().getValue();
		lex.advance();
		
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();
		
		Check.tokenType(lex, TokenType.STRING);
		facts.add(lex.currentToken());
		lex.advance();
		
		while (lex.currentToken().getTokenType() == TokenType.COMMA) {
			lex.advance();
			Check.tokenType(lex, TokenType.STRING);
			facts.add(lex.currentToken());
			lex.advance();
		}
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
		Check.tokenType(lex, TokenType.PERIOD);
		lex.advance();
	}
	
	public String toString() {
		String str = "";
		
		str += "  " + name + "(";
		
		for (Iterator<Token> factIter = facts.iterator(); factIter.hasNext(); ) {
			str += factIter.next().getValue();
			if (factIter.hasNext()) str += ",";
		}
		
		str += ").\n";
		
		return str;
	}
	
	public Fact clone() {
		ArrayList<Token> _facts = new ArrayList<Token>();
		for (Token t : facts) {
			_facts.add(t.clone());
		}
		return new Fact(new String(name), _facts);
	}
}
