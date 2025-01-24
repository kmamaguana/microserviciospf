package com.projectfinal.loginfashionhub.service;

import com.projectfinal.loginfashionhub.request.LoginRequest;
import com.projectfinal.loginfashionhub.response.AuthResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthService {

    AuthResponse login(LoginRequest request) throws AuthenticationException;

}
