package com.minhaempresa.spring.application.dtos;

public class TokenDTO {
    private String nome;
    private String token;

    public TokenDTO(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}