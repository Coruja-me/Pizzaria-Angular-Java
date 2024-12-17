package com.minhaempresa.spring.application.resources;

import com.minhaempresa.spring.application.dtos.TokenDTO;
import com.minhaempresa.spring.application.dtos.UserDTO;
import com.minhaempresa.spring.application.services.JwtImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserResource {
    @Autowired
    JwtImpl jwtImpl;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        String token = jwtImpl.buildToken(userDTO);
        TokenDTO tokenDTO = new TokenDTO(userDTO.getUser(), token);
        return ResponseEntity.ok(tokenDTO);
    }
}
