package repositories;

import entities.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioRol extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(String nombre);
}
