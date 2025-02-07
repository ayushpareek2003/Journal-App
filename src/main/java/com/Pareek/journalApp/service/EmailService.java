package com.Pareek.journalApp.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender Sender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            Sender.send(mail);
        } catch (Exception e) {
            log.error("Exception while sendEmail ", e);
        }
    }



}
