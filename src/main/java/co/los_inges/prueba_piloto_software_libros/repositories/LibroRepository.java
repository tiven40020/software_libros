package co.los_inges.prueba_piloto_software_libros.repositories;

import co.los_inges.prueba_piloto_software_libros.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {
}
