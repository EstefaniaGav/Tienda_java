package com.tienda.springboot.webapp.springboot_web.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdPedido;

    @Column(nullable = false, unique = true)
    private String numeroFactura;

    @Column(nullable = false)
    private String Descripción;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdCliente", referencedColumnName = "IdCliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IdEmpleado", referencedColumnName = "IdEmpleado", nullable = false)
    private Empleado empleado;
}
