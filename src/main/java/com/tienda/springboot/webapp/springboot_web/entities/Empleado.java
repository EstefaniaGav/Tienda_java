package com.tienda.springboot.webapp.springboot_web.entities;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
    private Integer idEmpleado;

    @Column(name = "Nombres", nullable = false)
    private String nombres;

    @Column(name = "Apellidos", nullable = false)
    private String apellidos;

    @Column(name = "TipoDocumento", nullable = false)
    private String tipoDocumento;
    
    @Column(name = "NumeroDocumento", nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "FechaCreacion", nullable = false)
    private Timestamp fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Fk_IdRol", referencedColumnName = "IdRol", nullable = false)
    private Rol idRol;
}
