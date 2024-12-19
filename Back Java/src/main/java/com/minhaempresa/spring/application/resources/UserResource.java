package com.minhaempresa.spring.application.resources;

import com.minhaempresa.spring.application.dtos.TokenDTO;
import com.minhaempresa.spring.application.dtos.UserDTO;
import com.minhaempresa.spring.application.services.JwtServiceImpl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserResource {

    @Autowired
    private JwtServiceImpl jwtService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        if(!"Teste".equals(userDTO.getUser()) || !"0000".equals(userDTO.getPassword())) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid Credentials");
        }

        String token = jwtService.buildToken(userDTO);
        TokenDTO tokenDTO = new TokenDTO(userDTO.getUser(), token);
        return ResponseEntity.ok(tokenDTO);
    }
}
