package com;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String codigo;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;
    
    @Size(max = 1000)
    private String descripcion;
    
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal precio;
    
    @NotBlank
    private String ubicacion;
    
    private boolean disponible;
    
    @Enumerated(EnumType.STRING)
    private TipoPublicacion tipo;
    
    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estado;
    
    private LocalDateTime fechaPublicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();
    
    private String horarioAtencion;

    // Getters y Setters
}