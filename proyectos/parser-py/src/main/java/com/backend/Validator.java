package com.backend;

import com.backend.lists.Node;

public class Validator {

    final String[] RESERVED_WORDS = new String[] {
            "as", "assert", "break", "class", "continue", "def", "del", "elif", "else", "except", "False",
            "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal",
            "pass", "raise", "return ", "True", "try", "while", "with",
    };

    final Character[] DELIMITERS = {
            '(', ')', '[', ']', '{', '}', '<', '>', '=', '+', '-', '*', '/', '%', '^', '&', '|', '!', '~',
            ';', ':', ',', '.', '?', '\'', '"', '`', ' ', '\t', '\n', '#'
    };

    final String[] OTHERS = {
            "(", ")", "{", "}", "[", "]", ",", ";", ":"
    };

    final String[] ARITHMETIC_OP = {
            "+", "-", "**", "/", "//", "%", "*"
    };

    final String[] ASSIGNMENT_OP = {
            "+=", "-=", "**=", "/=", "//=", "%=", "*=", "="
    };

    final String[] COMPARISION_OP = {
            "==", "!=", ">", "<", ">=", "<="
    };

    final String[] LOGIC_OP = {
            "and", "or", "not"
    };

    public boolean isOther(String currentString) {
        return includes(currentString, OTHERS);
    }

    public boolean isLogicOperator(String currentString) {
        return includes(currentString, LOGIC_OP);
    }

    public boolean isComparissionOperator(String currentString) {
        return includes(currentString, COMPARISION_OP);
    }

    public boolean isArithmeticOperator(String currentString) {
        return includes(currentString, ARITHMETIC_OP);
    }

    public boolean isAssignmentOperator(String currentString) {
        return includes(currentString, ASSIGNMENT_OP);
    }

    public boolean isKeyWord(String currentString) {
        return includes(currentString, RESERVED_WORDS);
    }

    public boolean isConstant(String currentString) {

        return (isString(currentString) || isNumber(currentString) || isDouble(currentString));

    }

    public boolean isString(String currentString) {
        return (currentString.length() > 1 && ((currentString.charAt(0) == '"'
                && currentString.charAt(currentString.length() - 1) == '"')
                || (currentString.charAt(0) == '\''
                        && currentString.charAt(currentString.length() - 1) == '\'')));
    }

    public boolean isDouble(String currentString) {
        try {
            Double.valueOf(currentString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isIdentifier(String currentString) {

        if (!Character.isLetter(currentString.charAt(0)) && currentString.charAt(0) != '_')
            return false;

        for (int i = 1; i < currentString.length(); i++) {
            if (!Character.isLetterOrDigit(currentString.charAt(i)) && currentString.charAt(i) != '_')
                return false;
        }

        return true;
    }

    public boolean isNumber(String currentString) {
        try {
            Integer.valueOf(currentString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isPossibleToken(Node<Character> currentChar) {
        return currentChar.getNext() == null || includes(currentChar.getNext().getContent(), DELIMITERS)
                || isDelimiter(currentChar.getContent());
    }

    public boolean isDelimiter(char ch) {
        return includes(ch, DELIMITERS);
    }

    public <T> boolean includes(T target, T[] arr) {
        for (T element : arr) {
            if (element.equals(target))
                return true;
        }
        return false;
    }

}
