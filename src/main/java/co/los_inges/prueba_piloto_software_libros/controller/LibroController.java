package co.los_inges.prueba_piloto_software_libros.controller;

import co.los_inges.prueba_piloto_software_libros.entities.Libro;
import co.los_inges.prueba_piloto_software_libros.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    @PostMapping()
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro){
        Libro newLibro = libroService.saveLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllBooks(){
        try {
            List<Libro> books = libroService.findAll();
            return ResponseEntity.ok(books);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Funcionalidad 1: Búsqueda básica por palabra en título o autor (ignora mayúsculas/minúsculas).
     * @param palabra
     * @return
     */
    @GetMapping("/buscar")
    public List<Libro> obtenerLibrosPorTituloAutor(@RequestParam String palabra){
        return libroService.buscarPorTituloAutor(palabra);
    }

    /**
     * Funcionalidad 2: Búsqueda avanzada combinando criterios de título, autor e ISBN.
     * @param titulo
     * @param autor
     * @param isbn
     * @return
     */
    @GetMapping("/filtrar")
    public List<Libro> filtrarLibro(@RequestParam (required = false) String titulo,
                                    @RequestParam (required = false) String autor,
                                    @RequestParam (required = false) String isbn){
        return libroService.filtrarLibro(titulo, autor, isbn);
    }


}
