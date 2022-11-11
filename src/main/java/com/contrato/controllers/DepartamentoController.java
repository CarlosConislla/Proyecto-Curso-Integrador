package com.contrato.controllers;

import com.contrato.models.Departamento;
import com.contrato.repository.DepartamentoRepository;
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
    DepartamentoRepository departamentoRepository;

    @GetMapping("/departamentos")
    public String ListarDepartamentos(Model model){
        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("departamentoList", departamentoList);

        return "departamentos";
    }

    @GetMapping("/departamentos/nuevo")
    public String MostrarFormulario(Model model){
        model.addAttribute("departamento", new Departamento());

        return "departamento_form";
    }

    @PostMapping("/departamentos/guardar")
    public String guardarDepartamento(Departamento departamento){
        departamentoRepository.save(departamento);

        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/editar/{id}")
    public String MostrarFormularioDeEditarDepartamento(@PathVariable("id") Long id, Model model){
        Departamento departamento = departamentoRepository.getById(id);
        model.addAttribute("departamento",departamento);

        return "departamento_form";
    }

    @GetMapping("/departamentos/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable("id") Long id, Model model){
        departamentoRepository.deleteById(id);

        return "redirect:/departamentos";
    }
}
