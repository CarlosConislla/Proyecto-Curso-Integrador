package com.contrato.services.impl;

import com.contrato.models.Empleado;
import com.contrato.services.EmpleadoService;
import com.contrato.util.ListarEmpleadoPDF;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmpleadoService empleadoService;

    @Value("${spring.mail.username}")
    private String email;

    public void SendMessage(String emailTo) {

        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(email);
            message.setTo(emailTo);
            message.setSubject("Aviso de Notificacion");
            message.setText("Se le informa de ...");
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SendReporte(String emailTo) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(email);
            helper.setTo(emailTo);
            helper.setSubject("Listado de Empleados");
            helper.setText("Se le adjunta la lista de Empleados de la Empresa");
            helper.addAttachment("Listado de Empleados", new File("C:\\Users\\user\\Documents\\Spring P\\Contratos\\reportes\\Empleados.pdf"));
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
