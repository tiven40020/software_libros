package co.los_inges.prueba_piloto_software_libros.repositories;

import co.los_inges.prueba_piloto_software_libros.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
