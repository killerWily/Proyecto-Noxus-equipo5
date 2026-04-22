package equipo5.nexus;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Entidad que representa un artículo genérico en el sistema NEXUS.
 * Hereda de PanacheEntity para utilizar el patrón Active Record,
 * lo que simplifica las operaciones de base de datos.
 */
@Entity
@Schema(name = "Producto", description = "Modelo de datos para un artículo del inventario")
public class ProductoModel extends PanacheEntity {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre descriptivo del producto", example = "Teclado Mecánico", required = true)
    public String nombre;

    @Positive(message = "El precio debe ser mayor a cero")
    @Schema(description = "Precio unitario de venta", example = "1250.0", required = true)
    public Double precio;

    @Schema(description = "Categoría o departamento", example = "Tecnología")
    public String categoria;

    // Nota: El campo 'id' es heredado automáticamente de PanacheEntity
}