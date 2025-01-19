package com.projectfinal.loginfashionhub.repository;

import com.projectfinal.loginfashionhub.model.AccountUnlockToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository for managing account unlock tokens.
 */
public interface AccountUnlockTokenRepository extends JpaRepository<AccountUnlockToken, Long> {

    /**
     * Find an AccountUnlockToken by its token value.
     *
     * @param token the token to search for
     * @return an Optional containing the AccountUnlockToken if found
     */
    Optional<AccountUnlockToken> findByToken(String token);

    /**
     * Delete all AccountUnlockTokens associated with a specific user.
     *
     * @param userId the ID of the user
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM AccountUnlockToken aut WHERE aut.user.id = :userId")
    void deleteByUserId(Integer userId);
}
