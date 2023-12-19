module com.example.project3client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports com.example.project3client.model;

    opens com.example.project3client to javafx.fxml;
    exports com.example.project3client;

}