package com.projectfinal.loginfashionhub.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for capturing admin registration information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequest {
    private String nombre;           // Nombre del usuario
    private String apellido;         // Apellido del usuario
    private String email;            // Correo electrónico del usuario
    private String role;             // Rol del usuario
}
