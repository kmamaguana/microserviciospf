package com.projectfinal.loginfashionhub.service.impl;

import com.projectfinal.loginfashionhub.jwt.JwtUtils;
import com.projectfinal.loginfashionhub.model.User;
import com.projectfinal.loginfashionhub.model.Role;
import com.projectfinal.loginfashionhub.repository.UserRepository;
import com.projectfinal.loginfashionhub.request.LoginRequest;
import com.projectfinal.loginfashionhub.request.RegisterRequest;
import com.projectfinal.loginfashionhub.response.AuthResponse;
import com.projectfinal.loginfashionhub.service.AuthService;
import com.projectfinal.loginfashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;
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

    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        try {
            User newUser = createNewUser(request);
            userRepository.save(newUser);

            String token = jwtService.generateToken(newUser.getUsername(), newUser.getRole().name());

            return ResponseEntity.ok(AuthResponse.builder()
                    .token(token)
                    .build());
        } catch (Exception e) {
            logger.error("Error durante el registro de usuario", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

        if (user.getScheduledForDeletion() != null) {
            userService.cancelAccountDeletion(user.getUsername());
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

    private User createNewUser(RegisterRequest request) {
        return User.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .accountLocked(false)
                .enabled(true)
                .failedLoginAttempts(0)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
