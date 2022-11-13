package com.contrato.controllers;

import com.contrato.models.Usuario;
import com.contrato.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;

@Controller
public class UsuarioController {

    @Autowired
    private IUsersService iUsersService;


    @GetMapping("/usuarios/nuevo")
    public String MostrarFormDeUsuario (Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuario_form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuarios(@ModelAttribute Usuario usuario, BindingResult bindingResult, Model model)
            throws ParseException {
        try {
            iUsersService.insert(usuario);
            return "redirect:/login";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "usuario_form";
        }
    }

    @GetMapping("/usuarios")
    public String ListarUsuarios(Model model){
        try {
            model.addAttribute("usuariosList", iUsersService.listAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "usuarios";
    }
}
