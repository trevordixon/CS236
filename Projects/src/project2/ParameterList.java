//REQUIRED CLASS
package project2;

import java.util.ArrayList;
import java.util.Iterator;

import project1.*;

public class ParameterList {
	ArrayList<Parameter> parameters;
	
	public ParameterList(LexicalAnalyzer lex) throws ParseException {
		parameters = new ArrayList<Parameter>();
		Parameter parameter;
		
		parameter = new Parameter(lex);
		parameters.add(parameter);
		
		while (Check.tokenTypeBool(lex, TokenType.COMMA)) {
			lex.advance();
			
			parameter = new Parameter(lex);
			parameters.add(parameter);
		}
	}
	
	public String toString() {
		String str = "";
		
		for (Iterator<Parameter> paramsIter = parameters.iterator(); paramsIter.hasNext(); ) {
			str += paramsIter.next().toString();
			if (paramsIter.hasNext()) str += ",";
		}
		
		return str;
	}
}
