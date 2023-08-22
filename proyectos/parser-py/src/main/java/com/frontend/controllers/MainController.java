package com.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleClassedTextArea;

public class MainController {

    @FXML
    Label lineCol;

    public void setLineCol(StyleClassedTextArea codeArea) {

        int caretPosition = codeArea.getCaretPosition();
        int lineNumber = codeArea.getCurrentParagraph();
        int columnNumber = caretPosition - codeArea.getAbsolutePosition(lineNumber, 0);
        lineCol.setText("Ln " + (lineNumber + 1) + ", Col " + (columnNumber + 1));

    }


}