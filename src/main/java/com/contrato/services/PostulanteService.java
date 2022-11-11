package com.contrato.services;

import com.contrato.models.Postulante;

import java.util.List;

public interface PostulanteService {

    void save(Postulante postulante);

    List<Postulante> listAll();

    void deleteById(int id);

    Postulante listById(int id);
}
