package com.backend.token;

public class Token {
    TokenType type;
    // TODO: ask about pattern
    String lexeme;
    int line;
    int column;


    public Token(TokenType type, String lexeme, int line, int absColumn) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;

        column = absColumn - lexeme.length() + 1;
    }

    public String toString() {
        return "Token, type: " + type.getValue() + ", lexeme: \"" + lexeme + "\", line: " + line + ", column: "
                + column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getLexeme() {
        return lexeme;
    }

    public TokenType getType() {
        return type;
    }
}
