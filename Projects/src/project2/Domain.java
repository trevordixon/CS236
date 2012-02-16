//REQUIRED CLASS
package project2;

import java.util.TreeSet;

import project1.*;

public class Domain {
	TreeSet<String> strings;
	
	public Domain(LexicalAnalyzer lex) {
		strings = new TreeSet<String>();
		
		for (Token token : lex) {
			if (token.getTokenType() == TokenType.STRING) {
				strings.add(token.getValue());
			}
		}
	}
	
	public int size() {
		return strings.size();
	}
	
	public String toString() {
		String str = "";
		
		for (String string : strings) {
			if (!str.isEmpty()) str += "\n";
			str += "  " + string;
		}
		
		return str;
	}
}
