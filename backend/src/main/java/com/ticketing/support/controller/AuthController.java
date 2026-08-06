package com.ticketing.support.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ticketing.support.dto.CompanyRegistrationRequest;


@RestController
@RequestMapping("/api/auth")
public class AuthController{
    @PostMapping("/register-company")
    public String registerCompany(@RequestBody CompanyRegistrationRequest request){

        return "Company registration request received for: "
        + request.getCompanyName();
    }
}