package com.frontend;

import com.backend.graph.Graph;
import com.backend.token.Token;
import com.backend.token.TokenType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;

public class GraphScene {

    ArrayList<Token> tokens;
    FlowPane tokensContainer;
    TokenType tokenType;
    Parent root;


    public GraphScene( Parent root) {
        this.root = root;
        tokensContainer = (FlowPane) root.lookup("#tokensContainer");
    }

    public void showTokensMatching(ArrayList<Token> tokens, TokenType tokenType) {
        this.tokenType= tokenType;
        this.tokens = getAllTokensMatching(tokens);
    }

    public ArrayList<Token> getAllTokensMatching (ArrayList<Token> tokens) {
        tokensContainer.getChildren().clear();
        ArrayList<Token> tokensMatching = new ArrayList<>();

        Label initialText = new Label("Lista de tokens: \"" + tokenType.getValue() + '"');
        initialText.getStyleClass().add("token-types__initial");
        tokensContainer.getChildren().add(initialText);

        for (int i = 0; i < tokens.size(); i++) {
            Token tk = tokens.get(i);
            if(tk.getType().equals(tokenType)) {
                tokensMatching.add(tk);
                Label label = new Label(tk.getLexeme());
                label.getStyleClass().add("token-types__token");
                tokensContainer.getChildren().add(label);
                label.setOnMouseClicked(event -> {
                    graphSelectedToken(tk);
                });
            }
        }
        return tokensMatching;
    }

    public void graphSelectedToken (Token token) {
        try {


            //create a threat that waits for the image to be created and then can show the image on screen.
            Graph graph = new Graph(token.getLexeme());
            graph.createPng();
            ImageView image = (ImageView) root.lookup("#graphImage");
            image.setImage(null);
            Label title = (Label) root.lookup("#graphTitle");

            title.setText(tokenType.getValue());
            image.setImage(new Image("graph.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
