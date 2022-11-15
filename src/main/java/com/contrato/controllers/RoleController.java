package com.contrato.controllers;

import com.contrato.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public String ListarRoles(Model model){
        try {
            model.addAttribute("rolesList", roleService.listAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Admin/roles";
    }

    @GetMapping("/roles/nuevo")
    public String MostrarFormDeRol(){
        return "Admin/role_form";
    }
}
