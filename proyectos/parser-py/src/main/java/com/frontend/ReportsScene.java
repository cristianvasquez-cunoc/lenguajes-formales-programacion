package com.frontend;

import com.backend.token.Token;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ReportsScene {

    Parent root;
    VBox vBox;
    ArrayList<Token> tokens;
    ObservableList<Token> observableTokens;
    TableView<Token> table;

    public ReportsScene(Parent root, ArrayList<Token> tokens) {
        this.root = root;
        this.tokens = tokens;

        Label title = new Label("Reportes");
        title.getStyleClass().add("title");
        title.setPadding(new Insets(16,16,16,16));
        vBox = (VBox) root.lookup("#vBox");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(title);

        table = new TableView<>();
        observableTokens = FXCollections.observableArrayList(tokens);

        TableColumn<Token, String> tokenCol = new TableColumn<>("Token");
        tokenCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().getValue()));
        tokenCol.setPrefWidth(220);

        TableColumn<Token, String> patternCol = new TableColumn<>("Patron");
        patternCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPattern()));
        patternCol.setPrefWidth(220);

        TableColumn<Token, String> lexemeCol = new TableColumn<>("Lexema");
        lexemeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLexeme()));
        lexemeCol.setPrefWidth(220);

        TableColumn<Token, String> lineCol = new TableColumn<>("Linea");
        lineCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLine())));
        lineCol.setPrefWidth(140);

        TableColumn<Token, String> colCol = new TableColumn<>("Columna");
        colCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getColumn())));
        colCol.setPrefWidth(140);


        table.getColumns().setAll(tokenCol, patternCol, lexemeCol, lineCol, colCol);

        table.setItems(observableTokens);

        vBox.getChildren().add(table);
        vBox.setVgrow(table, Priority.ALWAYS);

    }

    public void fillTable(ArrayList<Token> tokens) {
        observableTokens = FXCollections.observableArrayList(tokens);
        table.setItems(observableTokens);
    }
}
