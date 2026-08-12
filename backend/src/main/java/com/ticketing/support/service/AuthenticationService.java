package com.ticketing.support.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ticketing.support.repository.UserRepository;
import com.ticketing.support.dto.LoginRequest;
import com.ticketing.support.entity.User;
import com.ticketing.support.dto.LoginResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository =
        new HttpSessionSecurityContextRepository();

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public LoginResponse loginUser(LoginRequest request, HttpServletRequest httpRequest,
        HttpServletResponse httpResponse){
        String email = request.getEmail().trim().toLowerCase();

        User existedUser = userRepository
        .findByEmail(email)
        .orElseThrow(() ->
                new IllegalArgumentException("Email isn't registered."));

        
        boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            existedUser.getPasswordHash()
             );

        if (!passwordMatches){
            throw new IllegalArgumentException("Incorrect password.");
        } 

        Authentication authentication =
        new UsernamePasswordAuthenticationToken(
                existedUser.getEmail(),
                null,
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + existedUser.getRole().getName()
                        )
                )
        );
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        securityContext.setAuthentication(authentication);
        securityContextRepository.saveContext(
            securityContext,
            httpRequest,
            httpResponse);
        
        return new LoginResponse("Login successful");
    }
}