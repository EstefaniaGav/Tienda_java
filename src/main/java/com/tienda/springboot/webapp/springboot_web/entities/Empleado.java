package com.tienda.springboot.webapp.springboot_web.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Integer idEmpleado;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "tipoDocumento", nullable = false)
    private String tipoDocumento;
    
    @Column(name = "numeroDocumento", nullable = false, unique = true)
    private Integer numeroDocumento;

    @Column(name = "fechaCreacion", nullable = false)
    private String fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Fk_IdRol", referencedColumnName = "idRol", nullable = false)
    private Rol idRol;
}
