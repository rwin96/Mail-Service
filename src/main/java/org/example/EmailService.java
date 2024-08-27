package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private EmailSettings settings;
    private Session session;
    private static boolean flag = true;

    public EmailService() {
        settings = EmailSettings.getInstance();
        updateSettings();
    }

    public void sendEmail(String subject, String message, String recipient) throws MessagingException {
        if (flag)
            updateSettings();

        Message msg = createMessage(subject, message, recipient);
        Transport.send(msg);
    }

    private void getNewSession(Properties props) {
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(settings.getUsername(), settings.getPassword());
            }
        });
        flag = false;
    }

    public void updateSettings() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", settings.getServerAddress());

        if (settings.getEncryptionType() == EncryptionType.Plain) {
            properties.put("mail.smtp.port", String.valueOf(settings.getPort()));
        } else if (settings.getEncryptionType() == EncryptionType.SSL) {
            properties.put("mail.smtp.socketFactory.port", String.valueOf(settings.getPort()));
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "false");
        } else {
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", String.valueOf(settings.getPort()));
        }

        getNewSession(properties);
    }

    private Message createMessage(String subject, String msg, String recipient) {
        Message message = null;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(settings.getUsername()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(msg, "text/html");
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return message;
    }

    public static void setFlag(boolean flag) {
        EmailService.flag = flag;
    }
}
