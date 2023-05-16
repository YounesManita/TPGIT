package com.example.javaproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


// @author:DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "Formulaire"
public class formulaireController{
    //--------- bouton de login , retour & chargement d'image----------------------------------
    @FXML
    private Button backbtn;
    @FXML
    private Button loginbtn;
    @FXML
    private Button pikerphoto;
    //--------- les "textfield" pour l'ajout ---------------------------------------------------
    @FXML
    private TextField tfCin;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tftel;
    @FXML
    private TextField imgpath;
    //----------------------------- variable pour la connexion-------------------------------------
    Connection conn;
    //retour à la page précendente suite au clic de la bouton "backbtn"
    @FXML
    void GobackAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("lesClubsInterface.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
    // enregistrement des donéées saisies dans le formulaire en les envoyant vers la bases de donéées
    // pour qu'on puissent les afficher dans la "tableView" et les manipuler dans "espaceMembre"
    @FXML
   void submitOnAction(ActionEvent event) throws SQLException {
        if(tfCin.getText().equals("")||tfprenom.equals("")||tfnom.getText().equals("")||tftel.getText().equals("")||tfemail.getText().equals("")||tfdate.getValue().equals("")||imgpath.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("tous les champs du formulaire sont demandés ! ");
            alert.showAndWait();
        }
        else {
            insertRecord();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUBMISSION succed ! ");
            alert.showAndWait();
            try {

                // enregistrement des donées du formulaire dans un fichier
                // au cas il n y'a pas de connexion les donées seront enrgistrées d'une
                //maniére permanente localement
                FileWriter myWriter = new FileWriter("listDesMembresInscrits.txt",true);

                myWriter.write("***********************");
                myWriter.write("\n");
                myWriter.write(tfCin.getText());
                myWriter.write("\n");

                myWriter.write(tfprenom.getText());
                myWriter.write("\n");

                myWriter.write(tfnom.getText());
                myWriter.write("\n");

                myWriter.write(tftel.getText());
                myWriter.write("\n");

                myWriter.write(tfemail.getText());
                myWriter.write("\n");

                myWriter.write(tfdate.getValue().toString());
                myWriter.write("\n");

                myWriter.write(imgpath.getText());
                myWriter.write("\n");

                myWriter.write("\n");


                myWriter.close();
                System.out.println(" Successfully wrote to the file ");
            } catch (IOException e) {
                System.out.println(" An error occurred !!!! ");
                e.printStackTrace();
            }
        }
    }
    // excution de la requette d'insertion dans la base du données en cliquant sur "ajouterbtn"
    void insertRecord() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            final String url = "jdbc:mysql://localhost:3306/gestionclub";
            final String user = "root";
            final String pwd = "";

            String query = "INSERT INTO membreclub VALUES ('" + tfCin.getText() + "','" + tfnom.getText() + "','" + tfprenom.getText() + "','" + tfdate.getValue().toString() + "','" + tftel.getText() + "','" + tfemail.getText() + "','"
                    + imgpath.getText() + "','" + 1022 + "')";
            executeQuery(query);
        }catch(Exception e){
            e.getMessage();
        }
    }
    //Upload d'un fichier de type Image pour pouvoir aprées l'exploiter en génerant des cartes membres
    // l'ajout de " .replace("\\", "\\\\") " nous permet de conserver le path tel qu'il est car son importation
    // de la base provoque la disparition de '\'
    @FXML
   void photoSaveAction(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        File file = fileChooser.showOpenDialog(null);
        pikerphoto.getProperties().put("FILE_LOCATION", file.getAbsolutePath().replace("\\", "\\\\"));
        imgpath.setText(pikerphoto.getProperties().get("FILE_LOCATION").toString());
    }
    //c'est la connexion avec la base de données "gestionclub"
    // *** "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ***
    // cette partie entre deux "***" est ajouter à l'URL de connexion pour assure la syncronisation en terme
    // du temps et  d'emplacenmt du systeme
    public Connection getConnection(){
        Connection conn ;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionclub?useUnicode=true\n" +
                    "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&\n" +
                    "serverTimezone=UTC","root","");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error :"+e.getMessage());
            return null;
        }
    }
    //Exécuter des requêtes SQL directement sur la base de données.
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }}

}





