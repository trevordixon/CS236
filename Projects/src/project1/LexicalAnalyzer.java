package project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/** A lexical analyer driven by a finite state transducer.
 * The states and transitions conform exactly to the mathematical definition,
 * and the lexical analyzer contains additional logic to process line numbers,
 * trim whitespace, etc.
 */
public class LexicalAnalyzer implements Iterable<Token> {
    ArrayList<Token> tokenList = new ArrayList<Token>();
    StringBuilder valueBuilder = new StringBuilder();
    int tokenLineNumber = 1;
    int lineNumber = 1;

    public void tokenize(BufferedReader reader) throws IOException {
        final int EOF_VALUE = -1;
        State state = State.START;
        int c = 0;

        while (state != State.EOF) {
            c = reader.read();

            // Exit the current state and enter the next state.
            Transition transition = state.nextTransition(c);
            state = transition.getNextState();
            TokenType token_type = transition.getTokenType();

            // If the transition's output token type is non-null, then there
            // is a complete token to emit.  If it is null, then the value is
            // still being built, and there is nothing to emit.
            if (token_type != null) {
                emit(token_type);
            }

            if (c != EOF_VALUE) {
                valueBuilder.append((char)c);
            }

            // Update the line number if needed.
            if ((char)c == '\n') {
                lineNumber++;
            }
        }

        emit(TokenType.EOF);
        
        thisIter = this.iterator();
        advance();
    }

    /**
     * Creates and emits a new token with the given tokenType.
     * The valueBuilder is used to give the value of the token, and the
     * valueBuilder and line number are then reset for the next token.
     */
    private void emit(TokenType tokenType) {
        String value = valueBuilder.toString();
        Token t = new Token(tokenType, value, tokenLineNumber);

        // Strip out whitespace and convert keywords to the correct token
        // type.
        if (t.type == TokenType.WHITESPACE) {
            t = null;
        } else if (t.type == TokenType.ID) {
            if (t.value.equals("Schemes")) {
                t = new Token(TokenType.SCHEMES, t.value, t.lineNumber);
            } else if (t.value.equals("Facts")) {
                t = new Token(TokenType.FACTS, t.value, t.lineNumber);
            } else if (t.value.equals("Rules")) {
                t = new Token(TokenType.RULES, t.value, t.lineNumber);
            } else if (t.value.equals("Queries")) {
                t = new Token(TokenType.QUERIES, t.value, t.lineNumber);
            }
        }

        if (t != null) {
            tokenList.add(t);
        }

        valueBuilder.setLength(0);
        tokenLineNumber = lineNumber;
    }

    public int size() {
        return tokenList.size();
    }

    public Iterator<Token> iterator() {
        return tokenList.iterator();
    }

    private Iterator<Token> thisIter;
    private Token currentToken;
    
    public Token currentToken() {
    	return currentToken;
    }
    
    public void advance() {
    	currentToken = thisIter.next();
    }
    
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Token token: this) {
            output.append(token);
            output.append("\n");
        }
        output.append("Total Tokens = ");
        output.append(size());
        output.append("\n");
        return output.toString();
    }
}
