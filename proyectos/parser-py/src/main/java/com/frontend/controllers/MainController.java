package com.frontend.controllers;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainController {

    @FXML
    TextArea textArea;
    @FXML
    Label lineCol;

    public void applyColorsToText(KeyEvent event) throws IOException {

        setLineCol();

        if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
            String typedText = event.getText();
            String fullText = textArea.getText();
            System.out.println("Typed character: " + typedText + ". Text in area: " + fullText);
            // Apply your color changes here
        }
    }

    public void setLineCol() {
        int caretPosition = textArea.getCaretPosition();

        int line = textArea.getText(0, caretPosition).split("\n").length;
        int col = caretPosition - textArea.getText(0, caretPosition).lastIndexOf("\n");

        lineCol.setText("Ln " + line + ", Col " + col);
    }

    public void setLineColOnClck (MouseEvent e) throws IOException{
        setLineCol();
    }


}