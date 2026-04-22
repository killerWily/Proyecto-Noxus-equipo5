package equipo5.nexus;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
// Importaciones para documentación OpenAPI
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoController {

    // ============================================================
    // SECCIÓN 1: PANEL DE CONSULTAS (Lectura)
    // ============================================================

    @GET
    @Tag(name = "1. Panel de Consultas", description = "Lectura de inventario")
    @Operation(summary = "Listar todo el catálogo", description = "Retorna la lista completa de productos en el sistema.")
    public List<ProductoModel> listar() {
        return ProductoModel.listAll();
    }

    @GET
    @Path("/{id}")
    @Tag(name = "1. Panel de Consultas")
    @Operation(summary = "Buscar por ID", description = "Obtiene los detalles de un producto específico mediante su identificador.")
    public ProductoModel obtenerPorId(
            @Parameter(description = "ID del artículo", required = true) 
            @PathParam("id") Long id) {
        
        ProductoModel entidad = ProductoModel.findById(id);
        if (entidad == null) {
            throw new NotFoundException("Producto no localizado con ID: " + id);
        }
        return entidad;
    }

    // ============================================================
    // SECCIÓN 2: GESTIÓN DE REGISTROS (Escritura)
    // ============================================================

    @POST
    @Transactional
    @Tag(name = "2. Gestión de Registros", description = "Alta, Edición y Baja")
    @Operation(summary = "Dar de alta producto", description = "Crea un nuevo registro. El ID se genera automáticamente.")
    public ProductoModel insertar(ProductoModel producto) {
        // Garantizamos que el ID sea nulo para evitar errores de persistencia
        producto.id = null;
        producto.persist();
        return producto;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Tag(name = "2. Gestión de Registros")
    @Operation(summary = "Actualizar artículo", description = "Modifica los datos de un producto existente.")
    public ProductoModel actualizar(@PathParam("id") Long id, ProductoModel actualizado) {
        ProductoModel entidad = ProductoModel.findById(id);
        if (entidad == null) {
            throw new NotFoundException("No se pudo actualizar: ID no encontrado.");
        }
        
        // Actualización de campos
        entidad.nombre = actualizado.nombre;
        entidad.precio = actualizado.precio;
        entidad.categoria = actualizado.categoria;
        
        return entidad;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Tag(name = "2. Gestión de Registros")
    @Operation(summary = "Eliminar artículo", description = "Borra permanentemente el registro del sistema.")
    public void eliminar(@PathParam("id") Long id) {
        if (!ProductoModel.deleteById(id)) {
            throw new NotFoundException("No se pudo eliminar: ID no existe.");
        }
    }
}