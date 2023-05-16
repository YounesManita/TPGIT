package com.example.javaproject;
import entities.club;
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
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
// @author:DRISSI Houcem eddine & BOURAOUI manel
//Controlleur de l'interface graphique  "espaceClub"
public class clubController implements Initializable {
    //--------- "tableView" ----------------------------------------------------------------
    @FXML
    private TableView<club> tableclub;
    //--------- les "columns" du "tableView" ------------------------------------------------
    @FXML
    private TableColumn<club, Integer> Idcol;
    @FXML
    private TableColumn<club, String> Nomcol;
    @FXML
    private TableColumn<club, String> catcol;
    @FXML
    private TableColumn<club, String> cotisationcol;
    @FXML
    private TableColumn<club, String> nbrmembrecol;
    //--------- les "textfield" pour l'ajout , suppression & modification ------------------
    @FXML
    private TextField Idtf;
    @FXML
    private TextField cattf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField cottf;
    //--------- les boutons de l'ajout , suppression , modification & retour ------------------
    @FXML
    private Button backbtn;
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;


    //-------------------- variable compteur des membres ----------------------------------------

    // à chaque selection avec souris d'un tuple de la tableView les valeurs
    // du tuples seront mises dans les "textefield" pour les modifiers
    @FXML
    void handleMouseAction(MouseEvent event) {
        club cl= (club) tableclub.getSelectionModel().getSelectedItems().get(0);

        Idtf.setText(""+cl.getIdclub());
        nomtf.setText(""+cl.getNomc());
        cattf.setText(""+cl.getCatc());
        cottf.setText(""+cl.getCotisation());
    }
    // chaque clique sur l'un des boutons excute un traitement bien determiné
    // ajout avec "ajoutbtn"
    // suppression avec "supprimerbtn"
    // modification avec "modifierbtn"
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == ajouterbtn){
            insertRecord();
        }else if (event.getSource() == modifierbtn){
            updateRecord();
        }else if(event.getSource() == supprimerbtn){
            deleteButton();
        }
    }
    // excution de la requette d'insertion dans la base du données en cliquant sur "ajouterbtn"
    // et afficher le tuple dans la tableView

    @FXML
    void insertRecord() {
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

            String query = "INSERT INTO club (idclub, nomc, catc, nbrc) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, Idtf.getText());
            pst.setString(2, nomtf.getText());
            pst.setString(3, cattf.getText());
            pst.setString(4, cottf.getText());


            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Failed to insert record!");
            }

            showClubs();
        } catch (SQLException e) {
            e.printStackTrace();

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

            String query = "UPDATE club SET nomc = ?, catc = ?, Cotisation = ? WHERE idclub = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, nomtf.getText());
            pst.setString(2, cattf.getText());
            pst.setString(3, cottf.getText());
            pst.setString(4, Idtf.getText());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Failed to update record!");
            }

            showClubs();
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
        try{
            final String url = "jdbc:mysql://localhost:3306/gestionclub";
            final String user = "root";
            final String pwd = "";

        String query = "DELETE FROM club WHERE idclub =" +Idtf.getText() + "";
        executeQuery(query);
        showClubs();
        }catch(Exception e){
            e.getMessage();
        }
    }
    // le retour à la page précendente en cliquant sur la boutton "backbtn"
    @FXML
    void gobackOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginAdmin.fxml"));
        primaryStage.setScene(new Scene(root , 843,483));
        primaryStage.show();
    }
    //cette methode nous permet d'afficher le contenu de la base de donnés dans
    // la "tableView" dés le lancement de la fenetre contenant cette table
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showClubs();

        // en utilisant un arrow-fonction , on aboutit à calculer la cotisation totale de chaque club
        tableclub.setRowFactory(tv -> {
            TableRow<club> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Integer cotisationClub =Integer.parseInt(row.getItem().getCotisation());
                    Integer NombreMembreClub =Integer.parseInt(row.getItem().getNbrc());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("CALCUL DE COTISATION TOTALE ");
                    alert.setContentText("la cotisation totale de "+row.getItem().getNomc()+" est :" +cotisationClub*NombreMembreClub +"DT");
                    alert.showAndWait();}
            });
            return row;
        });
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
    public ObservableList<club> getClubList(){
        ObservableList<club> clubList = FXCollections.observableArrayList();

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
        String query = "SELECT * FROM club";

        Statement st;


            st = con.createStatement();
            rs=st.executeQuery(query);
            club cl;
            while(rs.next()){
                cl = new club(rs.getInt("idclub"),rs.getString("nomc"),rs.getString("catc"),rs.getString("nbrc"),rs.getString("Cotisation"));
                clubList.add(cl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0 ;i<clubList.size();i++ ){
            System.out.println(clubList.get(i));
        }
        return clubList;
    }
    // afficher les données extraites de la base du donéées au niveau de la "tableView"
    public void showClubs(){
        ObservableList<club> list = getClubList();
        Idcol.setCellValueFactory( new PropertyValueFactory<club,Integer>("idclub"));
        Nomcol.setCellValueFactory( new PropertyValueFactory<club,String>("nomc"));
        catcol.setCellValueFactory( new PropertyValueFactory<club,String>("catc"));
        nbrmembrecol.setCellValueFactory( new PropertyValueFactory<club,String>("nbrc"));
        cotisationcol.setCellValueFactory( new PropertyValueFactory<club,String>("Cotisation"));
        tableclub.setItems(list);
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