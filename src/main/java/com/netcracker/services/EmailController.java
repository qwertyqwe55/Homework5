package com.netcracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


import org.springframework.web.bind.annotation.*;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Controller
public class EmailController {
    @GetMapping("/sendemail")

    public String sendEmail() throws IOException, MessagingException {
        return "sendemail";
    }

    @PostMapping("/sendemail-get")
    public String getEmail(@RequestParam("email") String email, @RequestParam("text") String text,
                           @RequestParam("subject") String subject) throws IOException, MessagingException {

        sendmail(subject, email, text);
        return "succes";
    }


    private void sendmail(String subject, String email, String text) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dima.gusev123@yandex.ru", "azzqdppefebpyipt");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email,false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dima.gusev123@yandex.ru"));
        msg.setSubject(subject);
        msg.setContent(text, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(text, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        Transport.send(msg);
    }

}