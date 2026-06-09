package com.tienda.springboot.webapp.springboot_web.entities;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Integer idProducto;

    @Column(name = "NombreProducto", nullable = false)
    private String nombreProducto;

    @Column(name = "CantidadInventario", nullable = false)
    private Integer cantidadInventario;

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @Column(name = "FechaActualizacion")
    private Timestamp fechaActualizacion;
}
