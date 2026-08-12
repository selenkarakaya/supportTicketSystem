package com.ticketing.support.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import com.ticketing.support.dto.CompanyRegistrationRequest;
import com.ticketing.support.service.RegistrationService;

@RestController
@RequestMapping("/api/auth") //main route
public class AuthController{

    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register-company")
    public void registerCompany(
        @Valid @RequestBody CompanyRegistrationRequest request) {
            registrationService.registerCompany(request);
        }
}