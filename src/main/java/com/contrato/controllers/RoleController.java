package com.contrato.controllers;

import com.contrato.models.Departamento;
import com.contrato.models.Empleado;
import com.contrato.models.Role;
import com.contrato.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/roles/editar/{id}")//Metodo para modificar los datos del empleado
    public String MostrarFormularioDeEditarEmpleado(@PathVariable("id") Long id, Model model) {
        Role role = roleService.listById(id);
        model.addAttribute("rol",role);

        return "Admin/role_form";
    }

    @PostMapping("/roles/guardar")//Metodo para guardar un empleado
    public String guardarEmpleado(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }
}
