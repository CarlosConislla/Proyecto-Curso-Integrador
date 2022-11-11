package com.contrato.services.impl;

import com.contrato.models.Role;
import com.contrato.models.Usuario;
import com.contrato.repository.IRoleRepository;
import com.contrato.repository.IUsersRepository;
import com.contrato.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private IUsersRepository iUsersRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void insert(Usuario usuario) {
        Usuario objUser = usuario;

        objUser.setPassword(passwordEncoder.encode(objUser.getPassword()));

        Role role = new Role();
        // Se asigna el rol con el que queremos que el usuario se cree
        role.setAuthority("ROLE_USER");
        role = iRoleRepository.save(role);

        objUser.getRoles().add(role);
        objUser = iUsersRepository.save(objUser);
    }
}
