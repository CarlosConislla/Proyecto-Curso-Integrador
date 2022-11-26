package com.contrato.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    public void SendMessage(String emailTo){

        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(email);
            message.setTo(emailTo);
            message.setSubject("Mensaje");
            message.setText("Prueba de mensaje");
            mailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
