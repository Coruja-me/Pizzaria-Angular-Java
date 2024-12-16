package com.minhaempresa.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir todas as rotas
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Permitir o domínio de origem do frontend (Angular)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*");  // Cabeçalhos permitidos
    }
}
