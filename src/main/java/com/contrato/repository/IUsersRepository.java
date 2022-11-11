package com.contrato.repository;

import com.contrato.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
