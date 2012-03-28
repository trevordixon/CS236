//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class Scheme {
	public String name;
	public ArrayList<Token> attributes;
	
	public Scheme(String name, ArrayList<Token> attributes) {
		this.name = name;
		this.attributes = attributes;
	}
	
	public Scheme(LexicalAnalyzer lex) throws ParseException {
		attributes = new ArrayList<Token>();
		
		Check.tokenType(lex, TokenType.ID);
		name = lex.currentToken().getValue();
		lex.advance();
		
		Check.tokenType(lex, TokenType.LEFT_PAREN);
		lex.advance();
		
		Check.tokenType(lex, TokenType.ID);
		attributes.add(lex.currentToken());
		lex.advance();
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			Check.tokenType(lex, TokenType.ID);
			attributes.add(lex.currentToken());
			lex.advance();
		}
		
		Check.tokenType(lex, TokenType.RIGHT_PAREN);
		lex.advance();
	}
	
	public String toString() {
		String str = "";
		
		str += "  " + name + "(";
		
		for (Iterator<Token> attrIter = attributes.iterator(); attrIter.hasNext(); ) {
			str += attrIter.next().getValue();
			if (attrIter.hasNext()) str += ",";
		}
		
		str += ")\n";
		
		return str;
	}
	
	public Scheme clone() {
		ArrayList<Token> _attributes = new ArrayList<Token>();
		for (Token t : attributes) {
			_attributes.add(t.clone());
		}
		return new Scheme(new String(name), _attributes);
	}
}
