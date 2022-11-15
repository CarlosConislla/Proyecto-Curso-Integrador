package com.contrato.controllers;

import com.contrato.models.Departamento;
import com.contrato.repository.DepartamentoRepository;
import com.contrato.services.DepartamentoService;
import com.contrato.services.impl.DepartamentoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/departamentos")
    public String ListarDepartamentos(Model model){
        try {
            model.addAttribute("departamentoList", departamentoService.listAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Admin/departamentos";
    }

    @GetMapping("/departamentos/nuevo")
    public String MostrarFormulario(Model model){
        model.addAttribute("departamento", new Departamento());

        return "Admin/departamento_form";
    }

    @PostMapping("/departamentos/guardar")
    public String guardarDepartamento(Departamento departamento){
        departamentoService.save(departamento);

        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/editar/{id}")
    public String MostrarFormularioDeEditarDepartamento(@PathVariable("id") Long id, Model model){
        Departamento departamento = departamentoService.listById(id);
        model.addAttribute("departamento",departamento);

        return "Admin/departamento_form";
    }

    @GetMapping("/departamentos/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable("id") Long id, Model model){
        try {
            departamentoService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/departamentos";
    }
}
