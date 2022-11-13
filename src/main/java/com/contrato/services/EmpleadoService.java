package com.contrato.services;

import com.contrato.models.Empleado;
import com.contrato.models.Postulante;

import java.util.List;

public interface EmpleadoService {

    void save(Empleado empleado);

    List<Empleado> listAll();

    void deleteById(Long id);

    Empleado listById(Long id);
}
