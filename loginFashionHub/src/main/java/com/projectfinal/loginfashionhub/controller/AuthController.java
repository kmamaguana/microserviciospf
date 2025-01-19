package com.projectfinal.loginfashionhub.controller;

import com.projectfinal.loginfashionhub.request.LoginRequest;
import com.projectfinal.loginfashionhub.request.RegisterRequest;
import com.projectfinal.loginfashionhub.response.AuthResponse;
import com.projectfinal.loginfashionhub.service.AccountUnlockService;
import com.projectfinal.loginfashionhub.service.AuthService;
import com.projectfinal.loginfashionhub.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication-related operations.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;
    private final AccountUnlockService unlockService;

    /**
     * Endpoint to login a user.
     *
     * @param request DTO containing login details
     * @return ResponseEntity with the authentication token or error message
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Cuenta bloqueada.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    /**
     * Endpoint to register a new user.
     *
     * @param request DTO containing registration details
     * @return ResponseEntity with the authentication token
     */
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    /**
     * Endpoint to handle password reset requests.
     *
     * @param email User email
     * @return ResponseEntity with success message
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        passwordResetService.createAndSendResetToken(email);
        return ResponseEntity.ok("Se ha enviado un enlace de restablecimiento a su correo electrónico.");
    }

    /**
     * Endpoint to reset the password.
     *
     * @param token        Reset token
     * @param newPassword  New password
     * @return ResponseEntity with success message
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Su contraseña ha sido restablecida.");
    }

    /**
     * Endpoint to request account unlocking.
     *
     * @param email User email
     * @return ResponseEntity with success message or error if applicable
     */
    @PostMapping("/request-unlock")
    public ResponseEntity<?> requestUnlock(@RequestParam String email) {
        try {
            unlockService.createAndSendUnlockToken(email);
            return ResponseEntity.ok("Enlace de desbloqueo enviado a " + email + ". Por favor, revise su correo electrónico.");
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body("La cuenta no está bloqueada o ya está activa.");
        } catch (MailSendException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo enviar el correo electrónico de desbloqueo.");
        }
    }

    /**
     * Endpoint to unlock the account using the token.
     *
     * @param token Unlock token
     * @return ResponseEntity with success message
     */
    @PostMapping("/unlock-account")
    public ResponseEntity<?> unlockAccount(@RequestParam String token) {
        unlockService.unlockAccount(token);
        return ResponseEntity.ok("Cuenta desbloqueada.");
    }
}
