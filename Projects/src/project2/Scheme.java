//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class Scheme {
	String name;
	ArrayList<Token> attributes;
	
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
}
