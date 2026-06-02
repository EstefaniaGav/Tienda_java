package repositories;

import entities.Empleado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEmpleado extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByNumeroDocumento(String numeroDocumento);
}
