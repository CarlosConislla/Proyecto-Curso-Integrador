package com.contrato.controllers;

import com.contrato.services.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CorreoController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/correo")
    public String correo (){
        this.emailService.SendMessage(" saul.mayuri.b@gmail.com");
        return "redirect:/empleados";
    }
}
