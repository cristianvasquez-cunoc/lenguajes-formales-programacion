package com.frontend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    Label lineCol;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setLineCol(StyleClassedTextArea codeArea) {

        int caretPosition = codeArea.getCaretPosition();
        int lineNumber = codeArea.getCurrentParagraph();
        int columnNumber = caretPosition - codeArea.getAbsolutePosition(lineNumber, 0);
        lineCol.setText("Ln " + (lineNumber + 1) + ", Col " + (columnNumber + 1));

    }

}