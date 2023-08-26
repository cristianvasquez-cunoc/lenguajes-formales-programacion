package com.frontend;

import com.backend.graph.Graph;
import com.backend.graph.GraphLoader;
import com.backend.token.Token;
import com.backend.token.TokenType;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;

public class GraphScene {

    ArrayList<Token> matchingTokens;
    ArrayList<Token> tokens;
    FlowPane tokensContainer;
    TokenType tokenType;
    Parent root;
    ImageView tokenImage;
    Label tokenTitle;
    FlowPane tokensClasification;
    ArrayList<Label> tokensClasificationLabels;


    public GraphScene( Parent root, ArrayList<Token> tokens) {
        this.root = root;
        this.tokens = tokens;
        tokensContainer = (FlowPane)((ScrollPane) root.lookup("#tokensContainerScrollPane")).getContent();
    }

    public void showTokensMatching(TokenType tokenType) {
        this.tokenType= tokenType;
        getAllTokensMatching();
    }

    public void getAllTokensMatching () {
        tokensContainer.getChildren().clear();
        matchingTokens = new ArrayList<>();

        Label initialText = new Label("Lista de tokens: \"" + tokenType.getValue() + '"');
        initialText.getStyleClass().add("tokens-filtered__initial");
        tokensContainer.getChildren().add(initialText);

        for (int i = 0; i < tokens.size(); i++) {
            Token tk = tokens.get(i);
            if(tk.getType().equals(tokenType)) {
                matchingTokens.add(tk);
                Label label = new Label(tk.getLexeme());
                label.getStyleClass().add("tokens-filtered__token");
                tokensContainer.getChildren().add(label);
                label.setOnMouseClicked(event -> {
                    graphSelectedToken(tk);
                });
            }
        }
    }

    public void graphSelectedToken (Token token) {
        try {
            Graph graph = new Graph(token.getLexeme());
            graph.createPng();

            tokenImage = (ImageView) root.lookup("#graphImage");
            GraphLoader graphLoader = new GraphLoader(tokenImage, graph.getTime());
            graphLoader.start();

            tokenTitle = (Label) root.lookup("#graphTitle");
            tokenTitle.setText(tokenType.getValue());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTokenTypes() {
        tokensClasification = (FlowPane) root.lookup("#tokensClasification");
        TokenType[] tokensType = TokenType.values();
        tokensClasificationLabels = new ArrayList<Label>();
        for (int i = 0; i < tokensType.length; i++) {
            TokenType tkTp = tokensType[i];
            Label label = new Label(tkTp.getValue());
            label.getStyleClass().add("token-type");
            tokensClasification.getChildren().add(label);
            tokensClasificationLabels.add(label);
            label.setOnMouseClicked(event -> {
                removeFocusFromtokensClasificationLabels();
                label.getStyleClass().add("token-type-focus");
                showTokensMatching(tkTp);
            });
        }
    }

    public void removeFocusFromtokensClasificationLabels() {
        for (int i = 0; i < tokensClasificationLabels.size(); i++) {
            tokensClasificationLabels.get(i).getStyleClass().remove("token-type-focus");
        }
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }
}
