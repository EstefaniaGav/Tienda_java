package com.tienda.springboot.webapp.springboot_web.repositories;

import com.tienda.springboot.webapp.springboot_web.entities.Pedido;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPedido extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByNumeroFactura(String numeroFactura);
}

// Made with Bob
