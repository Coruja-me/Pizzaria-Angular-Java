package com.minhaempresa.spring.application.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhaempresa.spring.application.dtos.UserDTO;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        UserDTO userDTO = new UserDTO();

        if(login != null){
            userDTO.setUser(login);
            userDTO.setPswd("0000");
        }
        else{
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return User.builder()
            .username(userDTO.getUser())
            .password(passwordEncoder.encode(userDTO.getPassword()))
            .roles("USER")
            .build();
    }
}