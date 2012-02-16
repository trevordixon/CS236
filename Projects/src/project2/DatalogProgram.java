//REQUIRED CLASS
package project2;

import project1.*;

public class DatalogProgram {
	private SchemeList schemeList;
	private FactList factList;
	private RuleList ruleList;
	private QueryList queryList;
	private Domain domain;
	
	public DatalogProgram(LexicalAnalyzer lex) throws ParseException {
		// Schemes
		Check.tokenType(lex, TokenType.SCHEMES);
		lex.advance();
		Check.tokenType(lex, TokenType.COLON);
		lex.advance();
		schemeList = new SchemeList(lex);
		
		// Facts
		Check.tokenType(lex, TokenType.FACTS);
		lex.advance();
		Check.tokenType(lex, TokenType.COLON);
		lex.advance();
		factList = new FactList(lex);
		
		// Rules
		Check.tokenType(lex, TokenType.RULES);
		lex.advance();
		Check.tokenType(lex, TokenType.COLON);
		lex.advance();
		ruleList = new RuleList(lex);
		
		// Queries
		Check.tokenType(lex, TokenType.QUERIES);
		lex.advance();
		Check.tokenType(lex, TokenType.COLON);
		lex.advance();
		queryList = new QueryList(lex);
		
		// EOF
		Check.tokenType(lex, TokenType.EOF);
		
		// Domain
		domain = new Domain(lex);
	}
	
	public String toString() {
		String str = "";
		
		str += "Schemes(" + schemeList.size() + "):\n";
		str += schemeList.toString();
		
		str += "Facts(" + factList.size() + "):\n";
		str += factList.toString();
		
		str += "Rules(" + ruleList.size() + "):\n";
		str += ruleList.toString();
		
		str += "Queries(" + queryList.size() + "):\n";
		str += queryList.toString();
		
		str += "Domain(" + domain.size() + "):\n";
		str += domain.toString();
		
		return str;
	}
}
