package project1;

enum State {
    START {
        public Transition nextTransition(int c) {
            // The code to determine the next state after the start state is
            // in a separate method that is reused by other states.
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = null;
            return new Transition(nextState, tokenType);
        }
    },
    EOF {
        public Transition nextTransition(int c) {
            // The EOF state is never exited.
            return null;
        }
    },
    WHITESPACE {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (Character.isWhitespace((char)c)) {
                nextState = WHITESPACE;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.WHITESPACE;
            }
            return new Transition(nextState, tokenType);
        }
    },
    ID {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if ((c > 0) && (Character.isLetterOrDigit((char)c))) {
                nextState = ID;
            } else {
                nextState = whatWouldStartDo(c);
                // Set the token type to ID for now.  The keywords get
                // straightened out later (in the lexical analyzer).
                tokenType = TokenType.ID;
            }
            return new Transition(nextState, tokenType);
        }
    },
    STRING {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '\'') {
                nextState = STRING_QUOTE;
            } else if (c == -1) {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.UNDEFINED;
            } else {
                nextState = STRING;
            }
            return new Transition(nextState, tokenType);
        }
    },
    STRING_QUOTE {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '\'') {
                nextState = STRING;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.STRING;
            }
            return new Transition(nextState, tokenType);
        }
    },
    COMMENT_HASH {
    	public Transition nextTransition(int c) {
    		State nextState = null;
    		TokenType tokenType = null;
    		if (c == '|') {
    			nextState = MULTI_LINE_COMMENT;
    		} else {
    			nextState = SINGLE_LINE_COMMENT;
    		}
    		return new Transition(nextState, tokenType);
    	}
    },
    SINGLE_LINE_COMMENT {
    	public Transition nextTransition(int c) {
    		State nextState = null;
    		TokenType tokenType = null;
    		if (c == '\n' || c == '\r') {
    			nextState = whatWouldStartDo(c);
    			tokenType = TokenType.COMMENT;
    		} else {
    			nextState = SINGLE_LINE_COMMENT;
    		}
    		return new Transition(nextState, tokenType);
    	}
    },
    MULTI_LINE_COMMENT {
    	public Transition nextTransition(int c) {
    		State nextState = null;
    		TokenType tokenType = null;
    		if (c == '|') {
    			nextState = COMMENT_MIGHT_BE_OVER;
    		} else if (c == -1) {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.UNDEFINED;
    		} else {
    			nextState = MULTI_LINE_COMMENT;
    		}
    		return new Transition(nextState, tokenType);
    	}
    },
    COMMENT_MIGHT_BE_OVER {
    	public Transition nextTransition(int c) {
    		State nextState = null;
    		TokenType tokenType = null;
    		if (c == '#') {
    			nextState = COMMENT_OVER;
    		} else {
    			nextState = MULTI_LINE_COMMENT;
    		}
    		return new Transition(nextState, tokenType);
    	}
    },
    COMMENT_OVER {
    	public Transition nextTransition(int c) {
    		State nextState = whatWouldStartDo(c);
    		TokenType tokenType = TokenType.COMMENT;
    		return new Transition(nextState, tokenType);
    	}
    },
    COLON_OR_COLON_DASH {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '-') {
                nextState = COLON_DASH;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.COLON;
            }
            return new Transition(nextState, tokenType);
        }
    },
    COLON_DASH {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.COLON_DASH;
            return new Transition(nextState, tokenType);
        }
    },
    LT_OR_LE {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '=') {
                nextState = LE;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.LT;
            }
            return new Transition(nextState, tokenType);
        }
    },
    LE {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.LE;
            return new Transition(nextState, tokenType);
        }
    },
    GT_OR_GE {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '=') {
                nextState = GE;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.GT;
            }
            return new Transition(nextState, tokenType);
        }
    },
    GE {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.GE;
            return new Transition(nextState, tokenType);
        }
    },
    EQ {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.EQ;
            return new Transition(nextState, tokenType);
        }
    },
    MAYBE_NE {
        public Transition nextTransition(int c) {
            State nextState = null;
            TokenType tokenType = null;
            if (c == '=') {
                nextState = NE;
            } else {
                nextState = whatWouldStartDo(c);
                tokenType = TokenType.UNDEFINED;
            }
            return new Transition(nextState, tokenType);
        }
    },
    NE {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.NE;
            return new Transition(nextState, tokenType);
        }
    },
    COMMA {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.COMMA;
            return new Transition(nextState, tokenType);
        }
    },
    LEFT_PAREN {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.LEFT_PAREN;
            return new Transition(nextState, tokenType);
        }
    },
    PERIOD {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.PERIOD;
            return new Transition(nextState, tokenType);
        }
    },
    QMARK {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.Q_MARK;
            return new Transition(nextState, tokenType);
        }
    },
    RIGHT_PAREN {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.RIGHT_PAREN;
            return new Transition(nextState, tokenType);
        }
    },
    MULTIPLY {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.MULTIPLY;
            return new Transition(nextState, tokenType);
        }
    },
    ADD {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.ADD;
            return new Transition(nextState, tokenType);
        }
    },
    UNDEFINED {
        public Transition nextTransition(int c) {
            State nextState = whatWouldStartDo(c);
            TokenType tokenType = TokenType.UNDEFINED;
            return new Transition(nextState, tokenType);
        }
    }; // End of the list of states.


    /*
     * Determines which state to transition to on the given input character
     * and determines which token type to emit.
     * This method must be implemented by all of the enum constants.
     */
    public abstract Transition nextTransition(int c);

    public static State whatWouldStartDo(int c) {
        char ch = (char)c;
        if (c == -1) {
            return EOF;
        } else if (Character.isLetter(ch)) {
            return ID;
        } else if (Character.isWhitespace(ch)) {
            return WHITESPACE;
        } else {
            switch (ch) {
                case '\'':
                    return STRING;
                case ':':
                    return COLON_OR_COLON_DASH;
                case '<':
                	return LT_OR_LE;
                case '>':
                	return GT_OR_GE;
                case '=':
                	return EQ;
                case '!':
                	return MAYBE_NE;
                case ',':
                    return COMMA;
                case '(':
                    return LEFT_PAREN;
                case '.':
                    return PERIOD;
                case '?':
                    return QMARK;
                case ')':
                    return RIGHT_PAREN;
                case '*':
                    return MULTIPLY;
                case '+':
                    return ADD;
                case '#':
                	return COMMENT_HASH;
                default:
                    return UNDEFINED;
            }
        }
    }
}
