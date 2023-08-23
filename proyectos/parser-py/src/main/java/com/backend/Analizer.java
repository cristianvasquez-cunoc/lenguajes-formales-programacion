package com.backend;

import com.backend.lists.Node;
import com.backend.lists.Queue;
import com.backend.token.Token;

import java.util.ArrayList;

public class Analizer {

    Node<Character> currentChar;
    Accumulator acc;
    Queue<Character> characters;
    TokenGenerator tkGenerator;
    Validator validator;
    ArrayList<Token> tokens;

    public void start(String stringToAnalize) {

        characters = generateCharQueue(stringToAnalize);
        acc = new Accumulator("");
        tkGenerator = new TokenGenerator();
        validator = new Validator();
        tokens = new ArrayList<>();

        while (!characters.isEmpty()) {
            // remove char from characters and add it to accumulator
            currentChar = characters.getFront();
            acc.accumulate(characters.dequeue());

            // verify if token it's completed and if so, create it
            if (validator.isPossibleToken(currentChar)) {
                Token newTk = tkGenerator.generateToken(validator, acc, currentChar.getNext());

                if (newTk != null) {// means that it's a valid token
                    acc.empty();
                    tokens.add(newTk);
                }
            }

            // update tkGeneratorPointer
            tkGenerator.updatePointer(currentChar);
        }
    }

    public Queue<Character> generateCharQueue(String stringToAnalize) {

        Queue<Character> characters = new Queue<Character>();

        for (int i = 0; i < stringToAnalize.length(); i++) {
            characters.enqueue(stringToAnalize.charAt(i));
        }

        return characters;

    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
