package com.backend.token;

public enum TokenType {
    KEY_WORD("palabra reservada"),
    IDENTIFIER("identificadores"),
    ARITHMETIC_OP("operador aritmetico"),
    COMPARISION_OP("operador de comparacion"),
    LOGIC_OP("operador logico"),
    ASSIGNMENT_OP("operador asignacion"),
    CONSTANT("constante"),
    COMMENT("comentario"),
    ERROR("error"),
    OTHER("otro");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
