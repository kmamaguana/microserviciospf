package com.projectfinal.loginfashionhub.controller;

import com.projectfinal.loginfashionhub.request.RegisterAdminRequest;
import com.projectfinal.loginfashionhub.request.UserUpdateRequest;
import com.projectfinal.loginfashionhub.response.UserResponse;
import com.projectfinal.loginfashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling admin-related operations such as registering users and retrieving user information.
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    /**
     * Endpoint to register a new user by an admin.
     *
     * @param request DTO containing user registration details
     * @return ResponseEntity with success message
     */
    @PostMapping("/register-user")
    public ResponseEntity<String> registerUserByAdmin(@RequestBody RegisterAdminRequest request) {
        userService.registerUserByAdmin(request);
        return ResponseEntity.ok("Usuario registrado exitosamente. Se ha enviado un correo con las credenciales.");
    }

    /**
     * Endpoint to retrieve all users.
     *
     * @return ResponseEntity with a list of all users
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint to retrieve a user by their ID.
     *
     * @param id User ID
     * @return ResponseEntity with user details or a 404 not found response
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update user information.
     *
     * @param id         User ID
     * @param updatedUser DTO containing updated user details
     * @return ResponseEntity with success message or a 400 bad request response if validation fails
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest updatedUser) {
        try {
            userService.updateUser(id, updatedUser);
            return ResponseEntity.ok("Usuario actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Endpoint to delete a user.
     *
     * @param id User ID
     * @return ResponseEntity with success message or a 400 bad request response if validation fails
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
