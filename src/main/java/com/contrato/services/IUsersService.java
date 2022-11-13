package com.contrato.services;

import com.contrato.models.Departamento;
import com.contrato.models.Usuario;

import java.util.List;

public interface IUsersService {
    public void insert(Usuario usuario);

    List<Usuario> listAll();

    void deleteById(Long id);

    Usuario listById(Long id);
}
