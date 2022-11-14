package com.contrato.services;

import com.contrato.models.Postulante;
import com.contrato.models.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    List<Role> listAll();

    void deleteById(Long id);

    Role listById(Long id);
}
