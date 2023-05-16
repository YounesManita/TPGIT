package com.example.javaproject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
// @author:DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "hello-view"
public class mainController {
    //--------- les boutons pour aller autant qu'un admin ou consulter l'espace club------------------
    @FXML
    private Button adminbtn;
    @FXML
    private Button espaceClubbtn;
    //clic sur la bouton "adminbtn" pour aller au login d'admin
    @FXML
    void handleOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) adminbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginAdmin.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
    //clic sur la bouton "espaceClubbtn" pour aller au l'espace club
    @FXML
    void handleOnAction2(ActionEvent event) throws IOException {
        Stage stage =(Stage) espaceClubbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("lesClubsInterface.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
}