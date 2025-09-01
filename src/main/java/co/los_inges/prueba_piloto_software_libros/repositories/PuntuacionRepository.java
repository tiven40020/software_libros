package co.los_inges.prueba_piloto_software_libros.repositories;

import co.los_inges.prueba_piloto_software_libros.entities.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntuacionRepository extends JpaRepository<Puntuacion,Long> {
}
