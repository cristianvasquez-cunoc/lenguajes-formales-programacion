package com.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainController {

    @FXML
    TextArea textArea;

    public void applyColorsToText(KeyEvent event) throws IOException {

        textArea.setStyle("-fx--background--color: #ddd");

        if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
            String typedText = event.getText();
            System.out.println("Typed character: " + typedText + ". Text in area: " + textArea.getText());
            // Apply your color changes here
        }
    }
}