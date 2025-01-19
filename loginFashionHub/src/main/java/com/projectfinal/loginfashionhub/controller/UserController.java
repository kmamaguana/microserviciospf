package com.projectfinal.loginfashionhub.controller;

import com.projectfinal.loginfashionhub.request.PasswordChangeRequest;
import com.projectfinal.loginfashionhub.request.UserUpdateRequest;
import com.projectfinal.loginfashionhub.response.UserResponse;
import com.projectfinal.loginfashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for handling user-related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Endpoint to retrieve user information by username.
     *
     * @param username Username
     * @return ResponseEntity with user details or a 404 not found response
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update user information.
     *
     * @param updateRequest DTO containing updated user details
     * @return ResponseEntity with success message
     */
    @PutMapping("/update-info")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserUpdateRequest updateRequest) {
        userService.updateUserInfo(updateRequest);
        return ResponseEntity.ok("Información actualizada correctamente.");
    }

    /**
     * Endpoint to initiate account deletion.
     *
     * @param username Username
     * @return ResponseEntity with success message
     */
    @DeleteMapping("/delete-account/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        userService.initiateAccountDeletion(username);
        return ResponseEntity.ok("Solicitud de eliminación de cuenta recibida.");
    }

    /**
     * Endpoint to cancel account deletion.
     *
     * @param username Username
     * @return ResponseEntity with success message
     */
    @PostMapping("/cancel-deletion/{username}")
    public ResponseEntity<String> cancelAccountDeletion(@PathVariable String username) {
        userService.cancelAccountDeletion(username);
        return ResponseEntity.ok("La eliminación de la cuenta ha sido cancelada.");
    }

    /**
     * Endpoint to change password.
     *
     * @param passwordChangeRequest DTO containing password change details
     * @return ResponseEntity with success message
     */
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        userService.changePassword(passwordChangeRequest.getUsername(), passwordChangeRequest);
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }
}
