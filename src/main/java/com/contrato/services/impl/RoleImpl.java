package com.contrato.services.impl;

import com.contrato.models.Role;
import com.contrato.repository.IRoleRepository;
import com.contrato.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImpl implements RoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public void save(Role role) {
        iRoleRepository.save(role);
    }

    @Override
    public List<Role> listAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        iRoleRepository.deleteById(id);
    }

    @Override
    public Role listById(Long id) {
        return iRoleRepository.findById(id).get();
    }
}
