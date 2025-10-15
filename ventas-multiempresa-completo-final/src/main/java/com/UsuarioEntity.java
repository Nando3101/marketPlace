package com;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 10, max = 13)
    @Column(unique = true)
    private String cedula;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String nombre;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String apellido;
    
    @Email
    @NotBlank
    @Column(unique = true)
    private String correo;
    
    @Size(max = 15)
    private String telefono;
    
    @Size(max = 255)
    private String direccion;
    
    @Size(max = 20)
    private String genero;
    
    @NotBlank
    private String passwordHash;
    
    private boolean cuentaVerificada;
    
    private LocalDateTime fechaRegistro;
    
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    // Getters y Setters
}