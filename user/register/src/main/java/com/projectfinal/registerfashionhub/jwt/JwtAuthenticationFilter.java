package com.projectfinal.registerfashionhub.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter for handling JWT-based authentication for incoming HTTP requests.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    /**
     * Main filter logic for processing requests and validating JWT tokens.
     *
     * @param request     the incoming HTTP request
     * @param response    the outgoing HTTP response
     * @param filterChain the filter chain to pass the request/response further
     * @throws ServletException in case of servlet errors
     * @throws IOException      in case of input/output errors
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract and validate the token from the request
        String jwtToken = extractTokenFromHeader(request);
        if (StringUtils.hasText(jwtToken) && jwtUtils.validateToken(jwtToken)) {
            setAuthenticationContext(jwtToken, request);
        }

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the Authorization header.
     *
     * @param request the incoming HTTP request
     * @return the extracted token or null if not present
     */
    private String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validate header structure and extract the token
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Remove "Bearer " prefix
        }

        return null;
    }

    /**
     * Sets the authentication context for the current request if the token is valid.
     *
     * @param token   the JWT token
     * @param request the HTTP request
     */
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        String username = jwtUtils.getUsernameFromToken(token);

        // Load user details from the user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Create and set the authentication token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Update the SecurityContext with the authentication details
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
