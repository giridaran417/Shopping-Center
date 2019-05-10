package ServletApi;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) {
        sendmail("g","hi LKSHF");
    }

    public static boolean sendmail(String to,String msg) {
        String host = "smtp.gmail.com";
        final String user = "gi.com";//change accordingly
        final String password = "********";//change accordingly

     //   String to = "giri";//change accordingly
        String from = "giri";
        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("email verification");
            message.setText("Code is" + msg);
            //send the message
            Transport.send(message);
            System.out.println("message sent successfully...");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
