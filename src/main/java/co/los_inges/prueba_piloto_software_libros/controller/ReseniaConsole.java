package co.los_inges.prueba_piloto_software_libros.controller;

import co.los_inges.prueba_piloto_software_libros.entities.Libro;
import co.los_inges.prueba_piloto_software_libros.entities.Resenia;
import co.los_inges.prueba_piloto_software_libros.entities.Usuario;
import co.los_inges.prueba_piloto_software_libros.repositories.LibroRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.ReseniaRepository;
import co.los_inges.prueba_piloto_software_libros.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReseniaConsole implements CommandLineRunner {

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReseniaRepository reseniaRepository;

    public ReseniaConsole(LibroRepository libroRepository,
                          UsuarioRepository usuarioRepository,
                          ReseniaRepository reseniaRepository) {
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
        this.reseniaRepository = reseniaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        boolean continuarPrograma = true;
        Usuario usuarioActual = null;

        while (continuarPrograma) {
            // --- Seleccionar usuario ---
            if (usuarioActual == null) {
                System.out.println("\n👤 Lista de usuarios:");
                List<Usuario> usuarios = usuarioRepository.findAll();
                usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNombre()));

                System.out.print("👉 Digita el ID del usuario que escribe reseñas: ");
                Long idUsuario = sc.nextLong();
                sc.nextLine();

                Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
                if (usuarioOpt.isEmpty()) {
                    System.out.println("❌ No existe un usuario con ese ID.");
                    continue;
                }
                usuarioActual = usuarioOpt.get();
            }

            // --- Mostrar libros ---
            System.out.println("\n📚 Lista de libros disponibles:");
            List<Libro> libros = libroRepository.findAll();
            libros.forEach(libro ->
                    System.out.println(libro.getId() + " - " + libro.getTitulo() + " (Autor: " + libro.getAutor() + ")")
            );

            System.out.print("👉 Digita el ID del libro al que deseas hacer reseña: ");
            Long idLibro = sc.nextLong();
            sc.nextLine();

            Optional<Libro> libroOpt = libroRepository.findById(idLibro);
            if (libroOpt.isEmpty()) {
                System.out.println("❌ No existe un libro con ese ID.");
                continue;
            }
            Libro libroSeleccionado = libroOpt.get();

            // --- Escribir reseña ---
            System.out.print("✍️ Escribe tu reseña: ");
            String contenido = sc.nextLine();

            // --- Vista previa ---
            System.out.println("\n🔍 Vista previa de tu reseña:");
            System.out.println("Usuario: " + usuarioActual.getNombre());
            System.out.println("Libro: " + libroSeleccionado.getTitulo());
            System.out.println("Reseña: " + contenido);

            System.out.print("\n¿Deseas guardar esta reseña? (s/n): ");
            String confirmacion = sc.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                Resenia resenia = Resenia.builder()
                        .contenido(contenido)
                        .usuario(usuarioActual)
                        .libro(libroSeleccionado)
                        .fecha(LocalDateTime.now())
                        .build();

                reseniaRepository.save(resenia);
                System.out.println("✅ Reseña guardada con éxito!");
            } else {
                System.out.println("❌ Reseña descartada.");
            }

            // --- Menú para continuar ---
            System.out.println("\n¿Qué deseas hacer ahora?");
            System.out.println("1. Reseñar otro libro con el mismo usuario");
            System.out.println("2. Cambiar de usuario");
            System.out.println("3. Ver todas las reseñas guardadas");
            System.out.println("4. Salir");

            System.out.print("👉 Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("➡️ Continuando con el mismo usuario...");
                case 2 -> {
                    usuarioActual = null;
                    System.out.println("🔄 Cambiando de usuario...");
                }
                case 3 -> {
                    System.out.println("\n📖 Reseñas guardadas en el sistema:");
                    List<Resenia> resenias = reseniaRepository.findAll();
                    if (resenias.isEmpty()) {
                        System.out.println("⚠️ Aún no hay reseñas registradas.");
                    } else {
                        resenias.forEach(r -> {
                            System.out.println("-------------------------------------------------");
                            System.out.println("Usuario: " + r.getUsuario().getNombre());
                            System.out.println("Libro: " + r.getLibro().getTitulo());
                            System.out.println("Fecha: " + r.getFecha());
                            System.out.println("Contenido: " + r.getContenido());
                        });
                        System.out.println("-------------------------------------------------");
                    }
                }
                case 4 -> {
                    continuarPrograma = false;
                    System.out.println("👋 Gracias por usar el sistema de reseñas!");
                }
                default -> System.out.println("⚠️ Opción inválida, se mantendrá el mismo usuario.");
            }
        }

    }
}
