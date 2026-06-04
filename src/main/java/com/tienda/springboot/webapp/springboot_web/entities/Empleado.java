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
    private Integer IdEmpleado;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String tipoDocumento;
    
    @Column(nullable = false, unique = true)
    private Integer numeroDocumento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Fk_IdRol", referencedColumnName = "idRol", nullable = false)
    private Rol IdRol;
}
