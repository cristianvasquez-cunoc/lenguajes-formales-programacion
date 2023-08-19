package com.backend;

import com.backend.lists.*;

public class Accumulator {

    Queue<Character> queue;
    int columnInQueue;

    public Accumulator(String stringToAnalize) {

        queue = new Queue<>();
        columnInQueue = 1;

        for (int i = 0; i < stringToAnalize.length(); i++) {
            queue.enqueue(stringToAnalize.charAt(i));
        }
    }

    public String getString() {
        String string = "";
        Node<Character> currentChar = queue.getFront();

        for (int i = 0; i < queue.size(); i++) {
            string += currentChar.getContent();
            currentChar = currentChar.getNext();
        }

        return string;

    }

    public void accumulate(Character content) {
        queue.enqueue(content);
    }

    public void empty() {
        queue = new Queue<>();
    }

    public int getColumnInQueue() {
        return columnInQueue;
    }

    public void setColumnInQueue(int columnInQueue) {
        this.columnInQueue = columnInQueue;
    }

}
