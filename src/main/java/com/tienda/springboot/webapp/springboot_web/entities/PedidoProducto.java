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
    @Column(name = "id_pedido_productos")
    private Integer idPedidoProducto;

    @Column(name = "cantidad_compra", nullable = false)
    private Integer cantidadCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_productos", referencedColumnName = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_pedidos", referencedColumnName = "id_pedido", nullable = false)
    private Pedido pedido;
    
}
