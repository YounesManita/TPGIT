package com.example.javaproject;
import entities.membreClub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
// @author:DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "espaceMembre"
public class membreClubController implements Initializable {
    //--------- "tableView" ---------------------------------------------------------------------------------------
    @FXML
    private TableView<membreClub> tablemembre;
    //--------- les "columns" du "tableView" -----------------------------------------------------------------------
    @FXML
    private TableColumn<membreClub,String> Imagecol;
    @FXML
    private TableColumn<membreClub, Integer> Cincol;
    @FXML
    private TableColumn<membreClub,String> datenaissancecol;
    @FXML
    private TableColumn<membreClub,String> emailcol;
    @FXML
    private TableColumn<membreClub,String> nomcol;
    @FXML
    private TableColumn<membreClub,String> prenomcol;
    @FXML
    private TableColumn<membreClub,String> telephonecol;
    //--------- les "textfield" pour l'ajout , suppression & modification -------------------------------------------
    @FXML
    private TextField imagetf;
    @FXML
    private TextField cintf;
    @FXML
    private TextField datenaissancetf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField teltf;
    //--------- les boutons de l'ajout , suppression , modification, generation des cartes & retour ------------------
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button CarteGneratorbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    //chaque clique sur l'un des boutons excute un traitement bien determiné
    // ajout avec "ajoutbtn"
    // suppression avec "supprimerbtn"
    // modification avec "modifierbtn"
    @FXML
    void handleOnAction(ActionEvent event) {
        if(event.getSource() == ajouterbtn){
            insertRecord();
        }else if (event.getSource() == modifierbtn){
            updateRecord();
        }else if(event.getSource() == supprimerbtn){
            deleteButton();
        }
    }
    // le retour à la page précendente en cliquant sur la boutton "backbtn"
    @FXML
    void GobackAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginRepresentant.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();

    }
    // à chaque selection avec souris d'un tuple de la tableView les valeurs
    // du tuples seront mises dans les "textefield" pour les modifiers
    @FXML
    void handleMouseAction(MouseEvent event) {
        membreClub mm= (membreClub) tablemembre.getSelectionModel().getSelectedItems().get(0);
        cintf.setText(""+mm.getCIN());
        nomtf.setText(""+mm.getNom());
        prenomtf.setText(""+mm.getPrenom());
        emailtf.setText(""+mm.getEmail());
        datenaissancetf.setText(""+mm.getDaten());
        teltf.setText(""+mm.getTel());
        imagetf.setText(""+mm.getImage());
    }
    // excution de la requette d'insertion dans la base du données en cliquant sur "ajouterbtn"
    // et afficher le tuple dans la tableView
    void insertRecord() {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            final String url = "jdbc:mysql://localhost:3306/gestionclub";
            final String user = "root";
            final String pwd = "";

            System.out.println("Connexion en cours");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie");

            String query = "INSERT INTO membreclub (CIN, nom, prenom, email, daten, tel, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, cintf.getText());
            pst.setString(2, nomtf.getText());
            pst.setString(3, prenomtf.getText());
            pst.setString(4, emailtf.getText());
            pst.setString(5, datenaissancetf.getText());
            pst.setString(6, teltf.getText());
            pst.setString(7, imagetf.getText());


            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Failed to insert record!");
            }

            showMembers();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // excution de la requette du modification dans la base du données en cliquant sur "modifierbtn"
    // et modifier le tuple affiché dans la tableView
    @FXML
    void updateRecord() {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            final String url = "jdbc:mysql://localhost:3306/gestionclub";
            final String user = "root";
            final String pwd = "";

            System.out.println("Connexion en cours");
            con = DriverManager.getConnection(url, user, pwd);

            String query = "UPDATE membreclub SET nom = ?, prenom = ?, daten = ?, tel = ?, email = ?, image = ? WHERE CIN = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, nomtf.getText());
            pst.setString(2, prenomtf.getText());
            pst.setString(3, datenaissancetf.getText());
            pst.setString(4, teltf.getText());
            pst.setString(5, emailtf.getText());
            pst.setString(6, imagetf.getText());
            pst.setString(7, cintf.getText());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Failed to update record!");
            }

            showMembers();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // excution de la requette du suppression dans la base du données en cliquant sur "supprimerbtn"
    // et enlever le tuple affiché dans la tableView
    @FXML
    void deleteButton() {
        String query = "DELETE FROM membreclub WHERE CIN =" +cintf.getText() + "";
        executeQuery(query);
        showMembers();
    }
    //cette methode nous permet d'afficher le contenu de la base de donnés dans
    // la "tableView" dés le lancement de la fenetre contenant cette table
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMembers();
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
    //c'est la methode qui va extraire chaque donnes du table au niveau de la base
    // du données pour pouvoir l'exploiter aprés
    public ObservableList<membreClub> getMembreClubList(){
        ObservableList<membreClub> membreClubList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query ="SELECT * FROM membreclub";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs=st.executeQuery(query);
            membreClub mm;
            while(rs.next()){
                mm = new membreClub(rs.getInt("CIN"),rs.getString("nom"),rs.getString("prenom"),rs.getString("daten"),rs.getString("tel"),rs.getString("email"),rs.getString("image"));
                membreClubList.add(mm);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0 ;i<membreClubList.size();i++ ){
            System.out.println(membreClubList.get(i));
        }
        return membreClubList;
    }
    // afficher les données extraites de la base du donéées au niveau de la "tableView"
    public void showMembers(){
        ObservableList<membreClub> list = getMembreClubList();
        //ObservableList<membreClub> list = afficherApartirDeFichier();
        Cincol.setCellValueFactory( new PropertyValueFactory<membreClub,Integer>("CIN"));
        nomcol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("nom"));
        prenomcol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("prenom"));
        datenaissancecol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("daten"));
        emailcol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("email"));
        telephonecol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("tel"));
        Imagecol.setCellValueFactory( new PropertyValueFactory<membreClub,String>("image"));
        tablemembre.setItems(list);
        //tablemembre.setItems(list);
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
    //cette methode permet de transmettre les donéées selectionées du "espaceMmebre" vers "cartemembre"
    private void loadSceneAndSendMessage() {
        try {
            //charger la fenetre "cartemembre"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cartemembre.fxml"));
            Parent root = loader.load();

            //avoir un  controlleur de cartemembre
            carteMembreController cartemembre = loader.getController();
            //passer  les donéées
            String nm =nomtf.getText();
            String pm=prenomtf.getText();
            String dm = datenaissancetf.getText();
            String img = imagetf.getText().toString();
            Image image = new Image(img);
            cartemembre.imgviewer.setImage(image);
            cartemembre.nomlabel.setText(nm);
            cartemembre.prenomlabel.setText(pm);
            cartemembre.dateNlabel.setText(dm);
            //affichage de la fenetre aprés recupération du données
            Stage stage =(Stage) CarteGneratorbtn.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root , 843,483));
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    //generer une carte membre à travers les donéées selectionées de la tableView
    @FXML
    void GenererCarteMembre(ActionEvent event) throws IOException {
        // load the image
        /*String img = imagetf.getText().toString();
        Image image = new Image(img);
        imgviewer.setImage(image);*/
        loadSceneAndSendMessage();
        /*Stage stage =(Stage) CarteGneratorbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("cartemembre.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();*/
    }

    //cette methode permet d'extraire les données des membres à travers le fichier

    ObservableList<String> afficherApartirDeFichier(){
        ArrayList<membreClub> listeMembre = new ArrayList<membreClub>() ;
        File file = new File("listDesMembresInscrits.txt");
        ObservableList<String> data = FXCollections.observableArrayList();

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            if(file.isFile()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                listeMembre = (ArrayList<membreClub>)ois.readObject();
                System.out.println(listeMembre.get(0));
                ois.close();
            }else {
                System.out.print("Fichier introuvable!!");
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        listeMembre.stream().forEach(mm->data.addAll(String.valueOf(mm.getCIN()),mm.getNom(),mm.getPrenom(),mm.getDaten(),mm.getEmail(),mm.getTel(),mm.getImage()));
        System.out.println(data);
        return data;
    }
}



