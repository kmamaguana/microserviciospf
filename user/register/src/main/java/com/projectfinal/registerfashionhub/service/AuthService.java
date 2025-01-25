package com.projectfinal.registerfashionhub.service;

import com.projectfinal.registerfashionhub.request.RegisterRequest;
import com.projectfinal.registerfashionhub.response.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthResponse> register(RegisterRequest request);

}
