package com.backend;

import java.io.IOException;

import com.backend.graph.Graph;

public class Test {
    public static void main(String[] args) throws IOException {

        Graph gr = new Graph("while");
        gr.createPng();

    }
}

// Analizer analizer = new Analizer();

// String stringToAnalize = "++++++and.or.not.def if \"cadena\" : ( 4 + 5
// )\nmi_id_\n#commentario\nwhile\nfor\n>=\n<\n'cadena en comillas
// simples';;;;\n\n#un error se puede presentar como la siguiente linea\n\" mi
// cadena que no cierra";

// analizer.start(stringToAnalize);
// System.out.println("hola");