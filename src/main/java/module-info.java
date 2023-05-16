module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires java.activation;
    requires java.mail;

    exports com.example.javaproject;
    opens com.example.javaproject to javafx.fxml;
    exports entities;
    opens entities to javafx.fxml;

}