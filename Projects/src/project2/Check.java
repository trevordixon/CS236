package project2;

import project1.LexicalAnalyzer;
import project1.TokenType;

public class Check {
    public static void tokenType(LexicalAnalyzer lex, TokenType tokenType)
            throws ParseException
        {
    		while (lex.currentToken().getTokenType() == TokenType.COMMENT) lex.advance();
    		
            if (lex.currentToken().getTokenType() != tokenType){
                throw new ParseException(lex.currentToken());
            }
        }
    
    public static boolean tokenTypeBool(LexicalAnalyzer lex, TokenType tokenType) {
		while (lex.currentToken().getTokenType() == TokenType.COMMENT) lex.advance();
		return lex.currentToken().getTokenType() == tokenType;
    }
    
    public static void tokenType(LexicalAnalyzer lex, TokenType[] tokenTypes) throws ParseException {
		while (lex.currentToken().getTokenType() == TokenType.COMMENT) lex.advance();
		
		TokenType currentType = lex.currentToken().getTokenType();
		for (TokenType tokenType : tokenTypes) {
			if (currentType == tokenType) return;
		}
        throw new ParseException(lex.currentToken());
    }

    public static boolean tokenTypeBool(LexicalAnalyzer lex, TokenType[] tokenTypes) {
		while (lex.currentToken().getTokenType() == TokenType.COMMENT) lex.advance();
		
		TokenType currentType = lex.currentToken().getTokenType();
		for (TokenType tokenType : tokenTypes) {
			if (currentType == tokenType) return true;
		}
		return false;
    }
}
