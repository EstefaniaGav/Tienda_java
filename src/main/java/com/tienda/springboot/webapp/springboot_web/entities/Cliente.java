package com.tienda.springboot.webapp.springboot_web.entities;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idcliente")
    private Integer IdCliente;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "tipoDocumento", nullable = false)
    private String tipoDocumento;

    @Column(name = "numeroDocumento", nullable = false, unique = true)
    private Integer numeroDocumento;

    @Column(name = "segmentoCliente", nullable = false)
    private String segmentoCliente;

    @Column(name = "fechaNacimiento", nullable = false) 
    private Date fechaNacimiento;

    @Column(name = "fechaCreacion", nullable = false)  
    private Timestamp fechaCreacion;
}