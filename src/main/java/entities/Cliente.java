package entities;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdCliente;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String tipoDocumento;
    
    @Column(nullable = false, unique = true)
    private Integer numeroDocumento;
    
    @Column(nullable = false)
    private String segmentoCliente;

    @Column(nullable = false)
    private Date fechaNacimiento;
    
    private Timestamp fechaCreacion;
}
