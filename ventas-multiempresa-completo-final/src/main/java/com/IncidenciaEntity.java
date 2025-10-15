
package com;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencias")
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private LocalDateTime fechaIncidencia;
    
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;
    
    @NotBlank
    @Column(length = 1000)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderador_id")
    private Usuario moderador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Usuario vendedor;
    
    private boolean esApelacion;
    
    @Column(length = 1000)
    private String motivoApelacion;

    // Getters y Setters
}