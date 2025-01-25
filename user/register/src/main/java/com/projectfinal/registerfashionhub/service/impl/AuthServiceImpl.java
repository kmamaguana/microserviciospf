package com.projectfinal.registerfashionhub.service.impl;

import com.projectfinal.registerfashionhub.jwt.JwtUtils;
import com.projectfinal.registerfashionhub.model.User;
import com.projectfinal.registerfashionhub.repository.UserRepository;
import com.projectfinal.registerfashionhub.request.RegisterRequest;
import com.projectfinal.registerfashionhub.response.AuthResponse;
import com.projectfinal.registerfashionhub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementation of the AuthService interface, handling the registration process.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;  // Repository for user database operations
    private final JwtUtils jwtUtils;              // Utility for generating JWT tokens
    private final PasswordEncoder passwordEncoder;  // Encoder for hashing passwords
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);  // Logger for logging errors

    /**
     * Handles the user registration process.
     *
     * @param request DTO containing registration details
     * @return ResponseEntity containing the AuthResponse with the generated token or an error status
     */
    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        try {
            // Create a new user from the registration request
            User newUser = createNewUser(request);

            // Save the new user to the database
            userRepository.save(newUser);

            // Generate a JWT token for the new user
            String token = jwtUtils.generateToken(newUser.getUsername(), newUser.getRole().name());

            // Return the AuthResponse containing the token
            return ResponseEntity.ok(AuthResponse.builder()
                    .token(token)
                    .build());
        } catch (Exception e) {
            logger.error("Error during user registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Creates a new User object from the registration request.
     *
     * @param request DTO containing registration details
     * @return User object ready to be saved
     */
    private User createNewUser(RegisterRequest request) {
        return User.builder()
                .nombre(request.getNombre())           // User's first name
                .apellido(request.getApellido())       // User's last name
                .email(request.getEmail())              // User's email address
                .phoneNumber(request.getPhoneNumber())  // User's phone number
                .username(request.getUsername())        // Unique username for the user
                .password(passwordEncoder.encode(request.getPassword()))  // Encoded password
                .role(request.getRole())                // Role of the user (Vendor, Customer)
                .accountLocked(false)                  // Default to not locked
                .enabled(true)                          // Default to enabled
                .failedLoginAttempts(0)                // Default to no failed login attempts
                .createdAt(LocalDateTime.now())        // Record the creation date
                .build();
    }
}
