package com.contrato.controllers;

import com.contrato.services.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CorreoController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/correo")
    public String correo (){
        this.emailService.SendMessage(" cqcarlos10@gmail.com");
        return "redirect:/empleados";
    }

    @GetMapping("/correo/lista")
    public String correoLista () throws IOException, MessagingException {
        this.emailService.SendReporte(" cqcarlos10@gmail.com");
        return "redirect:/empleados";
    }
}
