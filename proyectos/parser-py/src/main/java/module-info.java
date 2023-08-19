module com.parserpy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.frontend to javafx.fxml;
    exports com.frontend;
    exports com.frontend.controllers;
    opens com.frontend.controllers to javafx.fxml;
}