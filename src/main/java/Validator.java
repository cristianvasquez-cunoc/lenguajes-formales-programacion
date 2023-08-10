import lists.Node;

public class Validator {

    final String[] RESERVED_WORDS = new String[] {
            "and", "as", "assert", "break", "class", "continue", "def", "del", "elif", "else", "except", "False",
            "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None", "nonlocal", "not", "or",
            "pass", "raise", "return ", "True", "try", "while", "with",
    };

    final Character[] DELIMITERS = {
            '(', ')', '[', ']', '{', '}', '<', '>', '=', '+', '-', '*', '/', '%', '^', '&', '|', '!', '~',
            ';', ':', ',', '.', '?', '\'', '"', '`', ' ', '\t', '\n'
    };

    public boolean isReservedWord(String currentString) {
        return includes(currentString, RESERVED_WORDS);
    }

    public boolean isToken(Node<Character> currentChar) {
        return currentChar.getNext() == null || includes(currentChar.getNext().getContent(), DELIMITERS);
    }

    public <T> boolean includes(T target, T[] arr) {
        for (T element : arr) {
            if (element.equals(target))
                return true;
        }
        return false;
    }

}
