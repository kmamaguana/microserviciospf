package com.projectfinal.registerfashionhub.controller;

import com.projectfinal.registerfashionhub.request.RegisterRequest;
import com.projectfinal.registerfashionhub.response.AuthResponse;
import com.projectfinal.registerfashionhub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Registers a new user in the system.
     *
     * @param request the registration details
     * @return a ResponseEntity containing the authentication response
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
