package com.projectfinal.loginfashionhub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for encapsulating the user response data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String nombre;           // Nombre del usuario
    private String apellido;         // Apellido del usuario
    private String email;            // Correo electrónico del usuario
    private String username;         // Nombre de usuario (debe ser único)
    private String role;             // Rol del usuario
}
