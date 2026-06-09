package com.tienda.springboot.webapp.springboot_web.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PedidoProductos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPedidoProductos")
    private Integer idPedidoProducto;

    @Column(name = "CantidadCompra", nullable = false)
    private Integer cantidadCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdProductos", referencedColumnName = "IdProducto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdPedidos", referencedColumnName = "IdPedido", nullable = false)
    private Pedido pedido;
    
}
