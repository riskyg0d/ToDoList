package com.ToDoList.util;

import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "YOUR_SECRET_KEY_HERE"; // Replace with a strong 32+ character key

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // 🔹 Ensure at least 32 characters
    }

    // ✅ Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), Jwts.SIG.HS256) // 🔹 Corrected method
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
        JwtParser parser = Jwts.parser()  // 🔹 No need for parserBuilder()
                .verifyWith(getSigningKey()) // 🔹 Updated method
                .build();
        return parser.parseSignedClaims(token).getPayload();
    }
}
