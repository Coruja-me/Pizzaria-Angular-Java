package com.minhaempresa.spring.application.services;

import com.minhaempresa.spring.application.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
    String buildToken(UserDTO userDTO);

    Claims getClaims(String token) throws ExpiredJwtException;

    boolean isValidToken(String token);

    String getLogin(String token);
}
