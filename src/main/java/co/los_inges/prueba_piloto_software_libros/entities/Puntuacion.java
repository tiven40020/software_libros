package co.los_inges.prueba_piloto_software_libros.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name= "puntuaciones")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valor;
    @ManyToOne(optional = false) private Usuario usuario;
    @ManyToOne(optional = false) private Libro libro;
    private LocalDateTime fecha;
}
