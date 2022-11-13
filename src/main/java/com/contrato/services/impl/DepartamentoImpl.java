package com.contrato.services.impl;

import com.contrato.models.Departamento;
import com.contrato.repository.DepartamentoRepository;
import com.contrato.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public void save(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    @Override
    public List<Departamento> listAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        departamentoRepository.deleteById(id);
    }

    @Override
    public Departamento listById(Long id) {
        return departamentoRepository.findById(id).get();
    }
}
