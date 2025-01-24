package com.projectfinal.loginfashionhub.service.impl;

import com.projectfinal.loginfashionhub.jwt.JwtUtils;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import com.projectfinal.loginfashionhub.request.LoginRequest;
import com.projectfinal.loginfashionhub.response.AuthResponse;
import com.projectfinal.loginfashionhub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final String USER_NOT_FOUND = "Usuario no encontrado con username/email: ";
    private static final String ACCOUNT_LOCKED = "Tu cuenta ha sido bloqueada debido a múltiples intentos fallidos.";
    private static final String INVALID_CREDENTIALS = "Credenciales inválidas.";

    @Override
    public AuthResponse login(LoginRequest request) throws AuthenticationException {
        String usernameOrEmail = request.getUsername();
        logger.info("Intento de inicio de sesión para: {}", usernameOrEmail);

        User user = findUserByUsernameOrEmail(usernameOrEmail);

        validateAccountStatus(user);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
            );

            resetFailedAttempts(user);

            String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
            logger.info("JWT token generado para el usuario: {}", user.getUsername());

            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (BadCredentialsException e) {
            handleFailedLoginAttempt(user);
            throw new BadCredentialsException(INVALID_CREDENTIALS + " Te quedan "
                    + (MAX_FAILED_ATTEMPTS - user.getFailedLoginAttempts()) + " intentos.");
        }
    }

    private User findUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByEmail(usernameOrEmail)
                .or(() -> userRepository.findByUsername(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + usernameOrEmail));
    }

    private void validateAccountStatus(User user) {
        if (user.isAccountLocked()) {
            logger.warn("Cuenta bloqueada para el usuario: {}", user.getUsername());
            throw new LockedException(ACCOUNT_LOCKED);
        }
    }

    private void resetFailedAttempts(User user) {
        if (user.getFailedLoginAttempts() > 0) {
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
        }
    }

    private void handleFailedLoginAttempt(User user) {
        int failedAttempts = user.getFailedLoginAttempts() + 1;
        user.setFailedLoginAttempts(failedAttempts);

        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            user.setAccountLocked(true);
            logger.error("Cuenta bloqueada para el usuario: {}", user.getUsername());
            throw new LockedException(ACCOUNT_LOCKED);
        }

        userRepository.save(user);
    }
}
