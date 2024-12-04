package com.abm.abm.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderUtil {

    @Autowired
    private JavaMailSender mailSender;
    public void sendEmailWithoutAttachment(String cc, String body, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rahulgoswami.rg2000@gmail.com");
            message.setCc(cc);
            message.setTo("rahulgoswami.rg2000@gmail.com");
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
