package com.ToDoList.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // Load from application.properties or environment variables
    private String secretKey;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    // ✅ Extract Username from JWT Token
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // ✅ Validate JWT Token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    // ✅ Corrected JWT Parsing
    private Claims parseClaims(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())  // Latest method in JJWT 2.x
                .build();
        return parser.parseSignedClaims(token).getPayload();
    }
}
