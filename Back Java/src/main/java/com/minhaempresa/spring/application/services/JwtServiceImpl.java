package com.minhaempresa.spring.application.services;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.minhaempresa.spring.application.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Service
public class JwtImpl implements JwtService {
    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.key}")
    private String jwtKey;

    private SecretKey getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    @Override
    public String buildToken(UserDTO userDTO) {
        long expiration = Long.parseLong(jwtExpiration);
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expiration);
        Date expirationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String expirationTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        return Jwts.builder()
                .subject(userDTO.getUser())
                .expiration(expirationDate)
                .claim("name", userDTO.getUser())
                .claim("expirationTime", expirationTime)
                .signWith(getSigningKey())
                .compact();
    }

    private String extractToken(String token) {
        return token.replace("Bearer ", "");
    }

    @Override
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(extractToken(token))
                .getPayload();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            LocalDateTime expiration = claims.getExpiration().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return !LocalDateTime.now().isAfter(expiration);
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getLogin(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
}
