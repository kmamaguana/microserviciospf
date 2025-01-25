package com.projectfinal.passwordforgotfashionhub.repository;

import com.projectfinal.passwordforgotfashionhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for handling user-related database operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the User if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by their email.
     *
     * @param email the email to search for
     * @return an Optional containing the User if found
     */
    Optional<User> findByEmail(String email);

}
