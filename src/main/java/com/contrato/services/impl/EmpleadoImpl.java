package com.contrato.services.impl;

import com.contrato.models.Empleado;
import com.contrato.models.Postulante;
import com.contrato.repository.DepartamentoRepository;
import com.contrato.repository.EmpleadoRepository;
import com.contrato.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> listAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado listById(Long id) {
        return empleadoRepository.findById(id).get();
    }
}
