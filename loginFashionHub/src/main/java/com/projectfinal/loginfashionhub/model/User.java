package com.projectfinal.loginfashionhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Represents a system user with different roles (Vendor or Customer).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Nombre del usuario

    @Column(nullable = false)
    private String apellido; // Apellido del usuario

    @Column(nullable = false, unique = true)
    private String email; // Correo único

    @Column(nullable = false, unique = true)
    private String username; // Nombre de usuario único (nickname)

    @Column(nullable = false)
    private String password; // Contraseña del usuario

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Rol asignado al usuario

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean accountLocked = false; // Indica si la cuenta está bloqueada

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enabled = true; // Indica si la cuenta está habilitada

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int failedLoginAttempts = 0; // Contador de intentos fallidos

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime scheduledForDeletion; // Fecha programada para eliminación

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean passwordChanged = false; // Indica si el usuario cambió su contraseña inicial

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; // Fecha de creación del usuario

    @Column(nullable = false)
    private String phoneNumber; // Número de teléfono del usuario, si aplica

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
