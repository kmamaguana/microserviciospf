package com.projectfinal.loginfashionhub.repository;

import com.projectfinal.loginfashionhub.model.User;
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

    /**
     * Check if an email already exists in the database.
     *
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Check if a username already exists in the database.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    boolean existsByUsername(String username);
}
