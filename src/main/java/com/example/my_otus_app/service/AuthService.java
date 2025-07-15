package com.example.my_otus_app.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.my_otus_app.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret}")
    private String jwtSecretString;
    private final Key jwtSecret;

    public AuthService(@Value("${jwt.secret}") String jwtSecretString) {
        this.jwtSecretString = jwtSecretString;
        this.jwtSecret = Keys.hmacShaKeyFor(jwtSecretString.getBytes());
    }

    public Optional<String> login(UUID id, String password) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT password FROM users WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String hashedPasswordFromDatabase = resultSet.getString("password");
                if (passwordEncoder.matches(password, hashedPasswordFromDatabase)) {
                    String token = generateJwtToken(id.toString());
                    return Optional.of(token);
                } else {
                    return Optional.empty();
                }
            } else {
                // Пользователь с таким id и password не найден
                return Optional.empty();
            }
        }
        catch (SQLException e) {
            System.err.println("Error getting user data: " + e.getMessage());
            return null;
        }
    }

    private String generateJwtToken(String userId) {
        long expirationTimeMs = 3600000; // 1 hour
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMs);

        return Jwts.builder()
                .subject(userId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(jwtSecret)
                .compact();
    }

    public Optional<String> validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();
            return Optional.ofNullable(userId); // Use ofNullable to handle potential null subject

        } catch (ExpiredJwtException e) {
            System.err.println("JWT is expired: " + e.getMessage());
            return Optional.empty();
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT is not supported: " + e.getMessage());
            return Optional.empty();
        } catch (MalformedJwtException e) {
            System.err.println("JWT is malformed: " + e.getMessage());
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error while validating token: " + e.getMessage());
            return Optional.empty();
        }
    }


}
