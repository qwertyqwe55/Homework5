package com.netcracker.controller;

import com.netcracker.services.EmailService;
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

    EmailService emailService = new EmailService();

    @GetMapping("/sendemail")
    public String sendEmail() throws IOException, MessagingException {
        return "sendemail";
    }

    @PostMapping("/sendemail-get")
    public String getEmail(@RequestParam("email") String email, @RequestParam("text") String text,
                           @RequestParam("subject") String subject) throws IOException, MessagingException {

        return emailService.sendmail(subject, email, text);
    }
}