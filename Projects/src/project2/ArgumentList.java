//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class ArgumentList implements Iterable<Argument> {
	public ArrayList<Argument> arguments;
	
	public ArgumentList(LexicalAnalyzer lex) throws ParseException {
		arguments = new ArrayList<Argument>();
		
		arguments.add(new Argument(lex));
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			arguments.add(new Argument(lex));
		}
	}
	
	public ArgumentList(ArrayList<Argument> arguments) {
		this.arguments = arguments;
	}
	
	public ArgumentList(ParameterList parameters) {
		arguments = new ArrayList<Argument>();
		for (Parameter p : parameters) {
			//arguments.add(p);
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
		return arguments.listIterator();
	}
	
	public ArgumentList clone() {
		ArrayList<Argument> a = new ArrayList<Argument>();
		for (Argument arg : arguments) {
			a.add(arg.clone());
		}
		return new ArgumentList(a);
	}
}
