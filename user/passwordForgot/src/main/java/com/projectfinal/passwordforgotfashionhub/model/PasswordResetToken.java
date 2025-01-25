package com.projectfinal.passwordforgotfashionhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents a token used to reset a user's password.
 * This token is generated when a user requests a password reset.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PasswordResetTokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for each token

    @Column(unique = true)
    private String token; // Token used for password reset

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user; // User associated with this token

    private Date expiryDate; // Token's expiration date

    private boolean used; // Indicates whether the token has already been used
}
