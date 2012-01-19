package project1;

import java.util.Formatter;

/**
 * To transition to another state, return a Transition instance.
 * The tokenType should be non-null if a token needs to be emitted, and
 * nextState should be the name of the class that needs to be transitioned to.
 */
class Transition {
    private final State nextState;
    private final TokenType tokenType;

    // Constructor
    Transition(State nextState, TokenType tokenType) {
        this.nextState = nextState;
        this.tokenType = tokenType;
    }

    // Access Methods
    public State getNextState() {
        return nextState;
    }
    public TokenType getTokenType() {
        return tokenType;
    }

    // Print Method
    public String toString() {
        Formatter f = new Formatter();
        f.format("Transition(%s,%s)", nextState, tokenType);
        return f.toString();
    }
}
