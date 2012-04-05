//REQUIRED CLASS
package project2;

import project1.*;

public class Query extends SimplePredicate
{
	public Query(LexicalAnalyzer lex) throws ParseException {
		super(lex);
	}

	public Query(String name, ParameterList parameters) {
		super(name, parameters);
	}
}
