package com.projectfinal.loginfashionhub.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for capturing user registration information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;           // Nombre del usuario
    private String apellido;         // Apellido del usuario
    private String email;            // Correo electrónico del usuario
    private String username;         // Nombre de usuario (debe ser único)
    private String password;         // Contraseña del usuario
}
