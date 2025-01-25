package com.projectfinal.passwordforgotfashionhub.controller;

import com.projectfinal.passwordforgotfashionhub.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication-related operations.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final PasswordResetService passwordResetService;

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

}
