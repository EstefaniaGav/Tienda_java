package com.tienda.springboot.webapp.springboot_web.repositories;

import com.tienda.springboot.webapp.springboot_web.entities.PedidoProducto;
import com.tienda.springboot.webapp.springboot_web.entities.Pedido;
import com.tienda.springboot.webapp.springboot_web.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPedidoProducto extends JpaRepository<PedidoProducto, Integer> {
    List<PedidoProducto> findByPedido(Pedido pedido);
    boolean existsByProducto(Producto producto);
}

// Made with Bob
