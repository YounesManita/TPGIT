package com.example.javaproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// @author:DRISSI Houcem eddine & BOURAOUI manel
public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),843,483);
        stage.setTitle("Welcome page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        launch();


    }
}