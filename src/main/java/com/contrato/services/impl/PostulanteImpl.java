package com.contrato.services.impl;

import com.contrato.models.Postulante;
import com.contrato.repository.PostulanteRepository;
import com.contrato.services.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteImpl implements PostulanteService {

    @Autowired
    PostulanteRepository postulanteRepository;

    @Override
    public void save(Postulante postulante) {
        postulanteRepository.save(postulante);
    }

    @Override
    public List<Postulante> listAll() {
        return postulanteRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        postulanteRepository.deleteById(id);
    }

    @Override
    public Postulante listById(int id) {
        return postulanteRepository.findById(id).get();
    }
}
