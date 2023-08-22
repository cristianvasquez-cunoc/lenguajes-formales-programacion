package com.frontend;

import com.backend.*;
import com.backend.token.Token;
import com.backend.token.TokenType;
import com.frontend.controllers.MainSceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.TwoDimensional;

import java.io.IOException;
import java.util.*;

public class MainScene extends Scene{

    MainSceneController controller;
    StyleClassedTextArea codeArea;
    int codeTextAreaSize;

    public MainScene(Parent parent, MainSceneController controller) {
        super(parent);
        this.controller = controller;
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

    public void addEventListeners() {
        codeArea.setOnKeyReleased(event -> {
            applyColors();
            controller.setLineCol(codeArea);
        });
        codeArea.setOnMouseClicked(mouseEvent -> {
            controller.setLineCol(codeArea);
        });
    }

    public void applyColors() {

        int newSize = codeArea.getText().length();

        if(!(codeTextAreaSize ==  newSize)) {
            codeTextAreaSize = newSize;

            Analizer analizer = new Analizer();
            analizer.start(codeArea.getText());
            ArrayList<Token> tokens = analizer.getTokens();

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


//    public void start(Stage stage) throws Exception {
//
//        try {
//            loader = new FXMLLoader(getClass().getResource("Main.fxml"));
//            root = loader.load();
//            controller = loader.getController();
//            createCodeArea(root);
//
//            Scene scene = new Scene(root);
//
//            setUpStage(stage);
//
//            String css = getClass().getResource("main-scene.css").toExternalForm();
//            scene.getStylesheets().add(css);
//            stage.setScene(scene);
//
//            stage.show();
//
//            addEventListeners();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }