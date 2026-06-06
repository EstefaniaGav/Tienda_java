package com.tienda.springboot.webapp.springboot_web.repositories;

import com.tienda.springboot.webapp.springboot_web.entities.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
}
