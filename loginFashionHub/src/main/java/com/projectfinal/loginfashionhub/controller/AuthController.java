package com.projectfinal.loginfashionhub.controller;

import com.projectfinal.loginfashionhub.request.LoginRequest;
import com.projectfinal.loginfashionhub.response.AuthResponse;
import com.projectfinal.loginfashionhub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
