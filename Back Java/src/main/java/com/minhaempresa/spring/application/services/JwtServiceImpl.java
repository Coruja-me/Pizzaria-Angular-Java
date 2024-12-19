package com.minhaempresa.spring.application.services;

import java.util.Date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.minhaempresa.spring.application.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.key}")
    private String jwtKey;

    @Override
    public String buildToken(UserDTO userDTO) {
        long expiration = Long.parseLong(jwtExpiration);
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expiration);
        Date expirationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String expirationTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        String token = Jwts.builder()
                .expiration(expirationDate)
                .subject(userDTO.getUser())
                .claim("expirationTime", expirationTime)
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();

        return "Bearer " + token;
    }


    @Override
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .build()
                .parseSignedClaims(token)
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
        }
    }

    @Override
    public String getLogin(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
}
