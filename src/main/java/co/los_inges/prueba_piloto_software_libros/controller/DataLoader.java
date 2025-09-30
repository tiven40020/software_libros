package co.los_inges.prueba_piloto_software_libros.controller;

import co.los_inges.prueba_piloto_software_libros.entities.Libro;
import co.los_inges.prueba_piloto_software_libros.entities.Usuario;
import co.los_inges.prueba_piloto_software_libros.repositories.LibroRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    public DataLoader(UsuarioRepository usuarioRepository, LibroRepository libroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) {
        // Solo cargar si las tablas están vacías
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(Usuario.builder()
                    .nombre("Juan Pérez")
                    .documento("123456")
                    .telefono("3001112233")
                    .correo("juan@mail.com")
                    .build());

            usuarioRepository.save(Usuario.builder()
                    .nombre("María López")
                    .documento("654321")
                    .telefono("3015556677")
                    .correo("maria@mail.com")
                    .build());
        }

        if (libroRepository.count() == 0) {
            libroRepository.save(Libro.builder()
                    .titulo("Cien Años de Soledad")
                    .isbn("9780307474728")
                    .autor("Gabriel García Márquez")
                    .precio(new BigDecimal("50000"))
                    .build());

            libroRepository.save(Libro.builder()
                    .titulo("El amor en los tiempos del cólera")
                    .isbn("9780307389732")
                    .autor("Gabriel García Márquez")
                    .precio(new BigDecimal("45000"))
                    .build());

            libroRepository.save(Libro.builder()
                    .titulo("La ciudad y los perros")
                    .isbn("9788420431846")
                    .autor("Mario Vargas Llosa")
                    .precio(new BigDecimal("48000"))
                    .build());
        }
    }
}
