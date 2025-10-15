
package com;

import com.multiempresa.ventas.model.entity.*;
import com.multiempresa.ventas.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public ProductoService(ProductoRepository productoRepository,
                         IncidenciaRepository incidenciaRepository,
                         UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.incidenciaRepository = incidenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PreAuthorize("hasRole('VENDEDOR')")
    public Producto crearProducto(Producto producto, Long vendedorId) {
        Usuario vendedor = usuarioRepository.findById(vendedorId)
            .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
            
        producto.setVendedor(vendedor);
        producto.setFechaPublicacion(LocalDateTime.now());
        producto.setEstado(EstadoPublicacion.ACTIVO);
        
        return productoRepository.save(producto);
    }

    @PreAuthorize("hasRole('MODERADOR') or hasRole('ADMINISTRADOR')")
    public void revisarProducto(Long productoId, boolean aprobar) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            
        producto.setEstado(aprobar ? EstadoPublicacion.ACTIVO : EstadoPublicacion.SUSPENDIDO);
        productoRepository.save(producto);
    }

    public List<Producto> buscarProductosActivos() {
        return productoRepository.findByEstado(EstadoPublicacion.ACTIVO);
    }

    @PreAuthorize("hasRole('COMPRADOR')")
    public void reportarProducto(Long productoId, String motivo, Long reportadorId) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            
        Usuario reportador = usuarioRepository.findById(reportadorId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion(motivo);
        incidencia.setEstado(EstadoIncidencia.PENDIENTE);
        incidencia.setFechaIncidencia(LocalDateTime.now());
        
        incidenciaRepository.save(incidencia);
    }
}