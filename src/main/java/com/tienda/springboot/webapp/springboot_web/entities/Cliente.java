package com.tienda.springboot.webapp.springboot_web.entities;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCliente")
    private Integer idCliente;

    @Column(name = "Nombres", nullable = false)
    private String nombres;

    @Column(name = "Apellidos", nullable = false)
    private String apellidos;

    @Column(name = "TipoDocumento", nullable = false)
    private String tipoDocumento;

    @Column(name = "NumeroDocumento", nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "SegmentoCliente")
    private String segmentoCliente;

    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "FechaCreacion")
    private Timestamp fechaCreacion;
}