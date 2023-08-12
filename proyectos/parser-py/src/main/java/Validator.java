import lists.Node;

public class Validator {

    final String[] RESERVED_WORDS = new String[] {
            "as", "assert", "break", "class", "continue", "def", "del", "elif", "else", "except", "False",
            "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal",
            "pass", "raise", "return ", "True", "try", "while", "with",
    };

    final Character[] DELIMITERS = {
            '(', ')', '[', ']', '{', '}', '<', '>', '=', '+', '-', '*', '/', '%', '^', '&', '|', '!', '~',
            ';', ':', ',', '.', '?', '\'', '"', '`', ' ', '\t', '\n', ' '
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

    public boolean isReservedWord(String currentString) {
        return includes(currentString, RESERVED_WORDS);
    }

    public boolean isPossibleToken(Node<Character> currentChar) {
        return currentChar.getNext() == null || includes(currentChar.getNext().getContent(), DELIMITERS)
                || includes(currentChar.getContent(), DELIMITERS);
    }

    public <T> boolean includes(T target, T[] arr) {
        for (T element : arr) {
            if (element.equals(target))
                return true;
        }
        return false;
    }

}
