import lists.Node;
import token.Token;
import token.TokenType;

public class TokenGenerator {
    int lineRead;
    int columnRead;
    TokenType tokenTypeRead;

    public TokenGenerator() {
        this.lineRead = 0;
        this.columnRead = 0;
    }

    public Token generateToken(Validator validator, Accumulator accumulator) {

        if (validator.isReservedWord(accumulator.getString())) {
            tokenTypeRead = TokenType.RESERVED_WORD;
        } else {
            tokenTypeRead = TokenType.ERROR;
        }
        ;

        return new Token(tokenTypeRead, accumulator.getString(), lineRead, columnRead);
    }

    public void updatePointer(Node<Character> currentChar) {
        // update line and column
        if (currentChar.getContent().equals('\n')) {
            lineRead++;
            columnRead = 0;
        } else {
            columnRead++;
        }

    }

}
