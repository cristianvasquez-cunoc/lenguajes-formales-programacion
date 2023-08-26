package com.backend.token;

public class Token {
    TokenType type;
    String pattern;
    String lexeme;
    int line;
    int column;


    public Token(TokenType type, String lexeme, int line, int absColumn) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;

        assignPattern();

        column = absColumn - lexeme.length() + 1;
    }

    public void assignPattern() {
        if(type.equals(TokenType.IDENTIFIER)) {
            pattern = "[a-zA-Z_][a-zA-Z0-9_]*";
        } else if (type.equals(TokenType.CONSTANT)) {
            pattern = "[a-zA-Z0-9.]";
        } else if (type.equals(TokenType.COMMENT)) {
            pattern = "#[a-zA-Z0-9.]*";
        } else {
            pattern = lexeme;
        }
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

    public String getPattern() {
        return pattern;
    }
}
