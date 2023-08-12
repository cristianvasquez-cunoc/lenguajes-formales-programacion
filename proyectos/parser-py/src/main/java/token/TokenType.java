package token;

public enum TokenType {
    RESERVED_WORD("reserved word"),
    IDENTIFIER("identifier"),
    ARITHMETIC_OP("arithmetic operator"),
    COMPARISION_OP("comparission operator"),
    LOGIC_OP("logic operator"),
    ASSIGNMENT_OP("assignment operator"),
    CONSTANT("constant"),
    COMMENT("comment"),
    ERROR("error"),
    OTHER("other");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
