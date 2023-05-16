package com.example.javaproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class carteMembreController {

    @FXML
    private Button backbtn;

    @FXML
    public Label dateNlabel;

    @FXML
    public ImageView imgviewer;

    @FXML
    public Label nomlabel;

    @FXML
    public Label prenomlabel;

    @FXML
    private Button sendbtn;

    @FXML
    void GobackAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("espaceMembre.fxml"));
        primaryStage.setScene(new Scene(root, 843, 483));
        primaryStage.show();

    }

    @FXML
    void SendByMail(ActionEvent event) throws MessagingException {
        String host = "smtp.gmail.com";
        String user = "cluby2022@gmail.com";
        String pass = "nnycibrmlwydwjgv";
        String from = "cluby2022@gmail.com";
        String subject = "[*** Bienvenue avec NOUS !! *** ]";
        boolean sessionDebug = false;

        Properties props = System.getProperties();
        try {
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress("drissihoucem2002@gmail.com")};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText("Pour pouvoir PRENDRE VOTRE CARTE MEMBRE veuillez contacter notre responsable");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("E-mail sended with SUCCESS ! ");
            alert.showAndWait();
        } catch (MessagingException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("E-mail FAILED !! ");
            alert.showAndWait();

        }

    }
}


