package com.example.javaproject;
import java.util.Date;;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class EmailUtil {
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("", ""));
            msg.setReplyTo(InternetAddress.parse("", true));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("e-mail envoyé avec SUCCEES ");
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}