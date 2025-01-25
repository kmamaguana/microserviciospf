package com.projectfinal.registerfashionhub.request;

import com.projectfinal.registerfashionhub.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for capturing user registration details.
 * This object is used to transfer registration data between the client and the service layer.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nombre;           // Nombre del usuario (nombre)
    private String apellido;         // Apellido del usuario
    private String email;            // Correo electrónico del usuario
    private String username;         // Nombre de usuario único
    private String password;         // Contraseña del usuario (debe cumplir con políticas de seguridad)
    private String phoneNumber;      // Número de teléfono del usuario
    private Role role;               // Rol del usuario (Vendor o Customer)
}
