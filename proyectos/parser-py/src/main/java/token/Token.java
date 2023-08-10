package token;

public class Token {
    TokenType type;
    // TODO: ask about patter
    String lexeme;
    int line;
    int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

}
