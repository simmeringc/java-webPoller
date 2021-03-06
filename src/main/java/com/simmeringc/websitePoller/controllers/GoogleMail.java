/**
 * Created by simmeringc on 4/28/17.
 *
 * https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * insecure TLS/SMTP mailer
 */

package com.simmeringc.websitePoller.controllers;

import static com.simmeringc.websitePoller.views.SystemLog.systemLogEmailFailed;
import static com.simmeringc.websitePoller.views.SystemLog.systemLogEmailSent;
import static org.apache.commons.lang3.StringUtils.abbreviate;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMail {

    public static void sendMail(String url, String email, Double threshold) {

        final String username = "simmeringcwebpoller@gmail.com";
        final String password = "4BHW8P8tS72h"; //I know.

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("SWP: reporting a " + threshold + "% html-diff at " + url);
            message.setText("Dear " + email + ",\n\n" + "Your web poller has detected a " + threshold + "% change at " + abbreviate(url, 30) + ".");

            Transport.send(message);
            systemLogEmailSent(email);

        } catch (MessagingException e) {
            systemLogEmailFailed(email);
            throw new RuntimeException(e);
        }
    }
}
