package com.projectfinal.registerfashionhub.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for handling JWT operations such as token generation, validation, and claims extraction.
 */
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Generates a JWT token for the given username and role.
     *
     * @param username the username of the user
     * @param role     the role of the user
     * @return a signed JWT token
     */
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(calculateExpirationDate())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates the provided JWT token.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid; false otherwise
     */
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token); // Ensure token is parsable
            return true;
        } catch (JwtException e) {
            // Log the exception for debugging purposes (if required)
            return false;
        }
    }

    /**
     * Extracts the username (subject) from the given token.
     *
     * @param token the JWT token
     * @return the username (subject) from the token
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * Extracts the role from the given token.
     *
     * @param token the JWT token
     * @return the role from the token
     */
    public String getRoleFromToken(String token) {
        return (String) getClaimsFromToken(token).get("role");
    }

    // --- Private Methods ---

    /**
     * Calculates the expiration date for a new token.
     *
     * @return the expiration date
     */
    private Date calculateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * Retrieves the claims from the provided token.
     *
     * @param token the JWT token
     * @return the claims extracted from the token
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Converts the secret key into a usable Key object for signing and validation.
     *
     * @return the signing key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
