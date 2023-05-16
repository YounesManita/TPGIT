package com.example.javaproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

// @author: DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "lesClubsInterface"
public class InterfaceClubController {
    //--------- les boutons de login autant qu'un representant du club ,de s'incrire à travers un formulaire& retour  ----------------------------
    @FXML
    private Button backbtn;
    @FXML
    private Button btnInscrire2;
    @FXML
    private Button btnenicRep;
    // le retour à la page précédente
    @FXML
    void GobackAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
    //aller pour se connecter autant qu'un representant pour gérer les membres du club
    @FXML
    void handleOnActionRep(ActionEvent event)throws IOException {
        Stage stage =(Stage) btnenicRep.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginRepresentant.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
    //aller à un formulaire pour s'inscrire à travers un formulaire & joindre la "team"
    @FXML
    void handleOnActioninscrire(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnInscrire2.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("formulaire.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
}
