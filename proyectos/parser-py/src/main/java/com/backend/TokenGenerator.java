package com.backend;

import com.backend.lists.Node;
import com.backend.token.*;

public class TokenGenerator {
    int lineRead;
    int columnRead;
    TokenType tokenTypeRead;

    boolean isGeneratingString = false;
    boolean isGeneratingComment = false;

    public TokenGenerator() {
        this.lineRead = 1;
        this.columnRead = 1;
    }

    public Token generateToken(Validator validator, Accumulator accumulator, Node<Character> nextCharNode) {

        Character nextChar;
        tokenTypeRead = null;
        String lexeme = accumulator.getString();
        try {
            nextChar = nextCharNode.getContent();
        } catch (NullPointerException e) {
            nextChar = '\0';

            if (isGeneratingString && !validator.isString(lexeme)) {
                return new Token(TokenType.ERROR, lexeme, lineRead, columnRead);
            }

        }

        // ignore spaces, tabs or line break
        if (lexeme.length() == 1
                && validator.includes(lexeme.charAt(0), new Character[] { '\t', '\n', ' ', '\r' })) {
            accumulator.empty();
            return null;
        }

        // handle comments
        if (lexeme.equals("#"))
            isGeneratingComment = true;

        if (isGeneratingComment && (nextChar.equals('\n') || nextChar.equals('\0'))) {
            isGeneratingComment = false;
            return new Token(TokenType.COMMENT, lexeme, lineRead, columnRead);
        }

        if (isGeneratingComment)
            return null;

        // handle constants strings
        if (lexeme.equals("\"") || lexeme.equals("'")) {
            isGeneratingString = true;
            return null;
        }

        if (isGeneratingString && !validator.isString(lexeme)) {
            return null;
        } else if (isGeneratingString) {
            isGeneratingString = false;
            return new Token(TokenType.CONSTANT, lexeme, lineRead, columnRead);
        }

        if ((nextChar.equals('\"') || nextChar.equals('\'')) && isGeneratingString)
            return null;

        // validate tokens
        if (validator.isKeyWord(lexeme)) {
            tokenTypeRead = TokenType.KEY_WORD;
        } else if (validator.isArithmeticOperator(lexeme)) {

            if (nextChar.equals('=')) // means token will be assignment_op on next loop
                return null;
            if (lexeme.equals("/") && nextChar.equals('/')) // means token it's gonna be //
                return null;

            tokenTypeRead = TokenType.ARITHMETIC_OP;
        } else if (validator.isComparissionOperator(lexeme)) {

            if ((lexeme.equals(">") || lexeme.equals("<")) && nextChar.equals('='))
                return null;// it's gonna be comparission with "="

            tokenTypeRead = TokenType.COMPARISION_OP;
        } else if (validator.isAssignmentOperator(lexeme)) {

            if (nextChar.equals('=') && lexeme.equals("=")) // means it's gonna be == comparission op
                return null;

            tokenTypeRead = TokenType.ASSIGNMENT_OP;
        } else if (validator.isLogicOperator(lexeme)) {
            tokenTypeRead = TokenType.LOGIC_OP;
        } else if (validator.isConstant(lexeme)) {

            if (nextChar.equals('.') && validator.isNumber(lexeme)) // it could be double
                return null;

            if (validator.isDouble(lexeme) && validator.isNumber(String.valueOf(nextChar)))
                return null;

            tokenTypeRead = TokenType.CONSTANT;

        } else if (validator.isIdentifier(lexeme)) {

            tokenTypeRead = TokenType.IDENTIFIER;

        } else if (validator.isOther(lexeme)) {

            tokenTypeRead = TokenType.OTHER;

        } else {

            if (lexeme.equals("!") && nextChar.equals('='))// it's gonna be assignment !=
                return null;

            tokenTypeRead = TokenType.ERROR;
        }
        ;

        return new Token(tokenTypeRead, lexeme, lineRead, columnRead);
    }


    public void updatePointer(Node<Character> currentChar) {
        // update line and column
        if (currentChar.getContent().equals('\n')) {
            lineRead++;
            columnRead = 1;
        } else {
            columnRead++;
        }

    }

}
