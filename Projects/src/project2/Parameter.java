//REQUIRED CLASS
package project2;

import project1.*;

public class Parameter
{
	ParameterInterface parameter;
	
	public Parameter(LexicalAnalyzer lex) throws ParseException {
		try {
			parameter = new Argument(lex);
		} catch (ParseException e1) {
			try {
				parameter = new Comparator(lex);
			} catch (ParseException e2) {
				parameter = new Expression(lex);
			}
		}
	}
	
	public String toString() {
		return parameter.toString();
	}
}
