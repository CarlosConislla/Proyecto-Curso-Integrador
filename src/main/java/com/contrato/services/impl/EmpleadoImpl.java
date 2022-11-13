package com.contrato.services.impl;

import com.contrato.models.Empleado;
import com.contrato.models.Postulante;
import com.contrato.services.EmpleadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoImpl implements EmpleadoService {

    @Override
    public void save(Empleado empleado) {

    }

    @Override
    public List<Empleado> listAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Empleado listById(Long id) {
        return null;
    }
}
