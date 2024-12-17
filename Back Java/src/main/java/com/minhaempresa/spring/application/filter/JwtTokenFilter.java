package com.minhaempresa.spring.application.filter;

import com.minhaempresa.spring.application.services.JwtService;
import com.minhaempresa.spring.application.services.SecurityUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private SecurityUserDetailsService suds;

    public JwtTokenFilter(JwtService jwtService, SecurityUserDetailsService suds) {
        this.jwtService = jwtService;
        this.suds = suds;
    }
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValidToken = Boolean.parseBoolean(jwtService.getLogin(token));
            if(isValidToken){
                String login = jwtService.getLogin(token);
                UserDetails userDetails = suds.loadUserByUsername(login);
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter(request, response);
    }
}
