package com.example.javaproject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.*;
// @author: DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "login representant"
public class repClubController {
    //--------- les "textfield" pour l'authentification ------------------
    @FXML
    private PasswordField tfpasswordrep;
    @FXML
    private TextField tfidrep;
    //--------- les boutons de login & retour ----------------------------
    @FXML
    private Button backbtn;
    @FXML
    private Button btnlogin;
    //--------- variables de connexion-------------------------------------
    Connection conn;
    ResultSet rs;

    //le retour à la page précendente
    @FXML
    void GobackAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("lesClubsInterface.fxml"));
        primaryStage.setScene(new Scene(root, 843, 483));
        primaryStage.show();
    }

    //aprés avoir connecter avec la base du données , on verifie si le mot de passe et l'Id
    // correspondent aux valeurs correctes stokées dans la base de données ou non
    @FXML
    void handleOnAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String ID = tfidrep.getText();
        String Password = tfpasswordrep.getText();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            final String url = "jdbc:mysql://localhost:3306/gestionclub";
            final String user = "root";
            final String pwd = "";

            System.out.println("Connexion en cours");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie");
            pst = con.prepareStatement("SELECT * FROM representantclub WHERE email=? AND mdp_user=?");
            pst.setString(1, ID);
            pst.setString(2, Password);
            rs = pst.executeQuery();

            if (rs.next()) {
                Stage stage = (Stage) btnlogin.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("espaceMembre.fxml"));
                primaryStage.setScene(new Scene(root, 843, 483));
                primaryStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Verifiez votre ID & Password ! ");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
