package com.minhaempresa.spring.application.services;

import java.util.Base64;
import java.util.Date;
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

    private Key getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtKey);
        return Keys.hmacShaKeyFor(decodedKey); 
    }

    @Override
    public String buildToken(UserDTO userDTO) {
        long expiration = Long.parseLong(jwtExpiration); 
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expiration);
        Date expirationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String expirationTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        String token = Jwts.builder()
                .subject(userDTO.getUser())
                .expiration(expirationDate)
                .claim("expirationTime", expirationTime)
                .signWith(getSigningKey()) 
                .compact();

        return "Bearer " + token;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(getSigningKey()) // Usar a chave para verificar a assinatura
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody(); // Retornar os claims extraídos do token
    }


    @Override
    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date date = claims.getExpiration();
            LocalDateTime expiration = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            boolean dateTimeIsAfterExpiration = LocalDateTime.now().isAfter(expiration);
            return !dateTimeIsAfterExpiration;
        }catch(ExpiredJwtException e){
            return false;
        }
    }

    @Override
    public String getLogin(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject(); 
    }
}
