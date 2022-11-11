package com.contrato.controllers;

import com.contrato.models.Departamento;
import com.contrato.models.Empleado;
import com.contrato.repository.DepartamentoRepository;
import com.contrato.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpleadoController {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @GetMapping("/empleados/nuevo")//Metodo para mostrar el formulario
    public String MostrarFormularioEmpleado(Model model) {
        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("departamentoList", departamentoList);

        return "empleado_form";
    }

    @PostMapping("/empleados/guardar")//Metodo para guardar un empleado
    public String guardarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);

        return "redirect:/empleados";
    }

    @GetMapping("/empleados")//Metodo para listar a los empleados
    public String ListarEmpleados(Model model) {
        List<Empleado> empleadoList = empleadoRepository.findAll();
        model.addAttribute("empleadoList", empleadoList);

        return "empleados";
    }

    @GetMapping("/empleados/editar/{id}")//Metodo para modificar los datos del empleado
    public String MostrarFormularioDeEditarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoRepository.getById(id);
        model.addAttribute("empleado",empleado);

        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("departamentoList", departamentoList);

        return "empleado_form";
    }

    @GetMapping("/empleados/eliminar/{id}")//Metodo para eliminar un empleado
    public String eliminarEmpleado(@PathVariable("id") Long id, Model model) {
        empleadoRepository.deleteById(id);

        return "redirect:/empleados";
    }
}
