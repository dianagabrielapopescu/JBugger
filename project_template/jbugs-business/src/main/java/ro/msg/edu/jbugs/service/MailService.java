package ro.msg.edu.jbugs.service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class MailService {
    public static void sendEmail(Integer deletedComments, Integer updatedBugs){
        Properties prop = System.getProperties();
        prop.put("mail.host", "smtp.gmail.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.setProperty("java.net.preferIPv4Stack", "true");

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("student.utcluj@gmail.com","wethebest");
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("student.utcluj@gmail.com"));
            InternetAddress[] address = {new InternetAddress("student.utcluj@gmail.com")};
            message.setRecipients(
                    Message.RecipientType.TO, address);
            message.setSubject("DIANA-deleted comments and updated bugs");

            String msg = "Deleted comments: " + deletedComments + "<br> Updated bugs: " + updatedBugs;

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect();
            Transport.send(message, address);
            transport.close();
        }catch (MessagingException me){
            me.printStackTrace();
        }
    }
}
