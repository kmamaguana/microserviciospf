package com.projectfinal.passwordresetfashionhub.controller;

import com.projectfinal.passwordresetfashionhub.service.PasswordResetService;
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

}
