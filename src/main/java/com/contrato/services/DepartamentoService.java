package com.contrato.services;

import com.contrato.models.Departamento;
import com.contrato.models.Postulante;

import java.util.List;

public interface DepartamentoService {

    void save(Departamento departamento);

    List<Departamento> listAll();

    void deleteById(Long id);

    Departamento listById(Long id);
}
