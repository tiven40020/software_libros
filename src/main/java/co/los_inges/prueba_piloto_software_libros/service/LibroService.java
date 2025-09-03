package co.los_inges.prueba_piloto_software_libros.service;

import co.los_inges.prueba_piloto_software_libros.entities.Libro;
import co.los_inges.prueba_piloto_software_libros.entities.Usuario;
import co.los_inges.prueba_piloto_software_libros.repositories.LibroRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.PuntuacionRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.ReseniaRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final PuntuacionRepository puntuacionRepository;
    private final ReseniaRepository reseniaRepository;
    private final UsuarioRepository usuarioRepository;

    public LibroService(LibroRepository libroRepository, PuntuacionRepository puntuacionRepository,
                           ReseniaRepository reseniaRepository, UsuarioRepository usuarioRepository){

        this.libroRepository = libroRepository;
        this.puntuacionRepository = puntuacionRepository;
        this.reseniaRepository = reseniaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUsuario(Usuario usuario){

        if (usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (usuario.getDocumento() == null || usuario.getDocumento().trim().isEmpty()){
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()){
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()){
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        return usuarioRepository.save(usuario);
    }

    public Libro saveLibro(Libro libro){

        if (libro == null){
            throw new IllegalArgumentException("El libbro no puede ser nulo");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()){
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()){
            throw new IllegalArgumentException("El ISBN es obligatorio");
        }
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()){
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (libro.getPrecio() == null){
            throw new IllegalArgumentException("El precio es obligatorio");
        }

        return libroRepository.save(libro);
    }

    public List<Libro> buscarPorTituloAutor(@RequestParam String palabra){
        return libroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(palabra, palabra);
    }

    public List<Libro> findAll(){
        return libroRepository.findAll();
    }

    public List<Libro> filtrarLibro(String titulo, String autor, String isbn) {
        return libroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCaseOrIsbnContainingIgnoreCase(titulo, autor, isbn);
    }
}
