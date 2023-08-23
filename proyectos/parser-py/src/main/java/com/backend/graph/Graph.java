package com.backend.graph;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class Graph {

    String lexeme;
    String dot;
    char start;
    char end;
    long time;

    public Graph(String lexeme) {
        // Escape double quotes if they are present within the lexeme
        this.lexeme = lexeme.replace("\"", "\\\"");

        start = lexeme.charAt(0);
        end = lexeme.charAt(lexeme.length() - 1);
    }

    public void createPng() throws IOException {

        try {
            String pathname = "./src/main/resources/com/frontend/graphs/graph";
            time = new Date().getTime();
            generateDot();
            MutableGraph graph = new Parser().read(dot);
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(pathname + time + ".png"));

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("cadena vacia");
        }


    }

    public void generateDot() {

        dot = "digraph G {rankdir=LR;" + verifyCharForDot(end)
                + " [shape=doublecircle];node [shape=circle];start [shape=point, width=0];start -> " + verifyCharForDot(start);

        int startI = ((int) start == 34) ? 2 : 1;
        int endI = ((int) start == 34) ? lexeme.length() - 2 : lexeme.length() - 1;

        for (int i = startI; i < endI; i++) {
            char currentCh = lexeme.charAt(i);
            dot += " -> " + verifyCharForDot(currentCh);
        }

        dot += " -> " + verifyCharForDot(end) + "; graph [bgcolor=\"#e2e8f0\"];}";

        System.out.println(dot);
    }

    private String verifyCharForDot(char ch) {
        if (ch == '<') {
            return "<menor que>";
        } else if(ch == '>') {
            return "<mayor que>";
        }else {
            return "<" + ch + ">";
        }
    }

    public long getTime() {
        return time;
    }
}