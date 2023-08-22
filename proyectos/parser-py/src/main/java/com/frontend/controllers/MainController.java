package com.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.fxmisc.richtext.StyleClassedTextArea;

public class MainSceneController {

    @FXML
    Label lineCol;

    @FXML
    Label graphicButton;

    public void setLineCol(StyleClassedTextArea codeArea) {

        int caretPosition = codeArea.getCaretPosition();
        int lineNumber = codeArea.getCurrentParagraph();
        int columnNumber = caretPosition - codeArea.getAbsolutePosition(lineNumber, 0);
        lineCol.setText("Ln " + (lineNumber + 1) + ", Col " + (columnNumber + 1));

    }

    public void showGraphicScene(MouseEvent event) {

    }


}