module com.example.project3client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.project3client to javafx.fxml;
    exports com.example.project3client;
}