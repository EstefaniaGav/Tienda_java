package com.tienda.springboot.webapp.springboot_web.repositories;

import com.tienda.springboot.webapp.springboot_web.entities.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProducto extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombreProducto(String nombreProducto);
}

// Made with Bob
