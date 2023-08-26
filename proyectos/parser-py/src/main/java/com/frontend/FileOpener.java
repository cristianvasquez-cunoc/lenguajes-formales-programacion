package com.frontend;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {

    Stage stage;
    CodeArea codeArea;
    String content;

    public void start(Stage stage, CodeArea codeArea) {

        this.stage = stage;
        this.codeArea = codeArea;
        this.content = "";

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            content = readFile(selectedFile);
            showContentInCodeArea();
        } else {
            System.out.println("no file");
        }
    }

    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void showContentInCodeArea() {
        codeArea.clear();
        codeArea.appendText(content);
    }

}
