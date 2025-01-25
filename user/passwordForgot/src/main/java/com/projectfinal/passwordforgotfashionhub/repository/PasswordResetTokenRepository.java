package com.projectfinal.passwordforgotfashionhub.repository;

import com.projectfinal.passwordforgotfashionhub.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing password reset tokens.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

}
