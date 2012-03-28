//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class ArgumentList implements Iterable<Argument> {
	ArrayList<Argument> arguments;
	
	public ArgumentList(LexicalAnalyzer lex) throws ParseException {
		arguments = new ArrayList<Argument>();
		
		arguments.add(new Argument(lex));
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			arguments.add(new Argument(lex));
		}
	}
	
	public String toString() {
		String str = "";
		
		for (Iterator<Argument> argsIter = arguments.iterator(); argsIter.hasNext(); ) {
			str += argsIter.next().toString();
			if (argsIter.hasNext()) str += ",";
		}
		
		return str;
	}

	@Override
	public Iterator<Argument> iterator() {
		return arguments.iterator();
	}
}
