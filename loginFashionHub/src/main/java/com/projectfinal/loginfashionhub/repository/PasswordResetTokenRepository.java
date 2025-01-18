package com.projectfinal.loginfashionhub.repository;

import com.projectfinal.loginfashionhub.model.PasswordResetToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository for managing password reset tokens.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    /**
     * Find a PasswordResetToken by its token value.
     *
     * @param token the token to search for
     * @return an Optional containing the PasswordResetToken if found
     */
    Optional<PasswordResetToken> findByToken(String token);

    /**
     * Delete all PasswordResetTokens associated with a specific user.
     *
     * @param userId the ID of the user
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken prt WHERE prt.user.id = :userId")
    void deleteByUserId(Integer userId);
}
