package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidoproductos")
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
