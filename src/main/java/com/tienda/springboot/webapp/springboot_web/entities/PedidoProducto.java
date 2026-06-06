package com.tienda.springboot.webapp.springboot_web.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidoproductos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdPedidoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdPedido", referencedColumnName = "IdPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdProducto", referencedColumnName = "IdProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidadCompra;
    
}
