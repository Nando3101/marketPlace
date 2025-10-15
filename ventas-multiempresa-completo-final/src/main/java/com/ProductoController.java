
package com;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/vendedor/{vendedorId}")
    public ResponseEntity<Producto> crearProducto(
            @PathVariable Long vendedorId,
            @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.crearProducto(producto, vendedorId));
    }

    @PutMapping("/{productoId}/revisar")
    public ResponseEntity<?> revisarProducto(
            @PathVariable Long productoId,
            @RequestParam boolean aprobar) {
        productoService.revisarProducto(productoId, aprobar);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productoId}/reportar")
    public ResponseEntity<?> reportarProducto(
            @PathVariable Long productoId,
            @RequestParam String motivo,
            @RequestParam Long reportadorId) {
        productoService.reportarProducto(productoId, motivo, reportadorId);
        return ResponseEntity.ok().build();
    }
}