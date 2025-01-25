package com.projectfinal.registerfashionhub.repository;

import com.projectfinal.registerfashionhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for handling database operations related to User entities.
 * This interface provides basic CRUD operations and custom query methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User entity by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the User if found, or empty if not found
     */
    Optional<User> findByUsername(String username);
}
