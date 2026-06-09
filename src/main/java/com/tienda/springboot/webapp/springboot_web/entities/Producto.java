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
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "cantidad_inventario", nullable = false)
    private Integer cantidadInventario;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;
}
