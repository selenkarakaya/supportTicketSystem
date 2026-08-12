package com.ticketing.support.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ticketing.support.dto.CompanyRegistrationRequest;
import com.ticketing.support.service.RegistrationService;
import com.ticketing.support.dto.CompanyRegistrationResponse;

import com.ticketing.support.service.AuthenticationService;
import com.ticketing.support.dto.LoginRequest;
import com.ticketing.support.dto.LoginResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth") //main route
public class AuthController{

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;

    public AuthController(RegistrationService registrationService, AuthenticationService authenticationService) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register-company")
    public CompanyRegistrationResponse registerCompany(
        @Valid @RequestBody CompanyRegistrationRequest request) {
          return  registrationService.registerCompany(request);
        }

    @PostMapping("/login-user")
    public LoginResponse loginUser(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {
        
        return authenticationService.loginUser(
                request,
                httpRequest,
                httpResponse
            );
        }

    @GetMapping("/me")
    public String getCurrentUser() {
        return "Authenticated";
        }
}