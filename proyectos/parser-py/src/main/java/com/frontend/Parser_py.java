package com.frontend;

import com.backend.*;
import com.backend.token.Token;
import com.backend.token.TokenType;
import com.frontend.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.TwoDimensional;

import java.io.IOException;
import java.util.*;

public class Parser_py extends Application {

    MainController mainController;
    StyleClassedTextArea codeArea;
    ArrayList<Token> tokens;
    int codeTextAreaSize;

    GraphScene graphScene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        tokens = new ArrayList<>();

        try {
            setUpStage(stage);
            String CSS = getClass().getResource("styles.css").toExternalForm();

            FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent rootMain = loaderMain.load();
            mainController = loaderMain.getController();
            createCodeArea(rootMain);
            Scene mainScene = new Scene(rootMain);
            mainScene.getStylesheets().add(CSS);

            FXMLLoader loaderGraph = new FXMLLoader(getClass().getResource("Graph.fxml"));
            Parent rootGraph = loaderGraph.load();
            Scene graphScene = new Scene(rootGraph);
            graphScene.getStylesheets().add(CSS);
            this.graphScene = new GraphScene(rootGraph,tokens);
            this.graphScene.loadTokenTypes();

            stage.setScene(mainScene);
            stage.show();

            // Handle navigation action (button click)
            Button switchToGraphSceneButton = (Button) rootMain.lookup("#switchToGraphScene");
            switchToGraphSceneButton.setOnAction(event -> {
                stage.setScene(graphScene);
                this.graphScene.setTokens(tokens);

            });

            Button switchToMainSceneButton = (Button) rootGraph.lookup("#switchToMainScene");
            switchToMainSceneButton.setOnAction(event -> {
                stage.setScene(mainScene);
            });

            addEventListeners();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCodeArea(Parent root) {
        AnchorPane anchorPane = (AnchorPane) root.lookup("#anchorPaneWithCodeArea");

        codeArea = new CodeArea();
        codeTextAreaSize = 0;

        AnchorPane.setTopAnchor(codeArea, 30.0);
        AnchorPane.setRightAnchor(codeArea, 0.0);
        AnchorPane.setBottomAnchor(codeArea, 30.0);
        AnchorPane.setLeftAnchor(codeArea, 0.0);

        anchorPane.getChildren().add(codeArea);
    }

    public void setUpStage (Stage stage) {
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Parser py");
        stage.setWidth(960);
        stage.setHeight(760);
        stage.setResizable(false);
    }

    public void addEventListeners() {
        codeArea.setOnKeyReleased(event -> {
            applyColors();
            mainController.setLineCol(codeArea);
        });
        codeArea.setOnMouseClicked(mouseEvent -> {
            mainController.setLineCol(codeArea);
        });
    }

    public void applyColors() {

        int newSize = codeArea.getText().length();

        if(!(codeTextAreaSize ==  newSize)) {
            codeTextAreaSize = newSize;

            Analizer analizer = new Analizer();
            analizer.start(codeArea.getText());
            tokens = analizer.getTokens();

            for (int i = 0; i < tokens.size(); i++) {

                Token tk = tokens.get(i);

                int startLine = tk.getLine() - 1;
                int startColumn = tk.getColumn() - 1;
                TwoDimensional.Position startPosition = codeArea.position(startLine, startColumn);
                int start = startPosition.toOffset();
                int end = start + tk.getLexeme().length();

                List<String> styleClasses = new ArrayList<>();

                String classColor = "default-text";

                if(Arrays.asList(TokenType.ARITHMETIC_OP, TokenType.COMPARISION_OP, TokenType.LOGIC_OP,TokenType.ASSIGNMENT_OP).contains(tk.getType())) {
                    classColor = "skyblue-text";
                }else if(tk.getType().equals(TokenType.IDENTIFIER)) {
                    classColor = "white-text";
                }else if(tk.getType().equals(TokenType.KEY_WORD)) {
                    classColor = "purple-text";
                }else if(tk.getType().equals(TokenType.CONSTANT)) {
                    classColor = "red-text";
                } else if (tk.getType().equals(TokenType.COMMENT)) {
                    classColor = "gray-text";
                } else if (tk.getType().equals(TokenType.OTHER)) {
                    classColor = "green-text";
                } else if (tk.getType().equals(TokenType.ERROR)) {
                    classColor = "error-text";
                }

                styleClasses.add(classColor);

                codeArea.setStyle(start, end, styleClasses);

            }

        }
    }


}