package com.ticketing.support.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.support.repository.CompanyRepository;
import com.ticketing.support.repository.UserRepository;
import com.ticketing.support.repository.RoleRepository;
import com.ticketing.support.dto.CompanyRegistrationRequest;
import com.ticketing.support.entity.Company;
import com.ticketing.support.entity.User;
import com.ticketing.support.entity.Role;
import com.ticketing.support.dto.CompanyRegistrationResponse;


@Service
public class RegistrationService{


    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegistrationService(
        CompanyRepository companyRepository,
        UserRepository userRepository,
        RoleRepository roleRepository){

            this.companyRepository = companyRepository;
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
    }

    @Transactional
    public CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest request) {

        String email = request.getAdminEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Company company = new Company();
        
        company.setName(request.getCompanyName());
        company.setEmail(email);

        companyRepository.save(company);


        User user = new User();

        user.setFullName(request.getAdminFullName());
        user.setEmail(email);
        user.setCompany(company);

        Role adminRole = roleRepository
        .findByName("COMPANY_ADMIN")
        .orElseThrow(() ->
                new IllegalStateException("COMPANY_ADMIN role not found."));

        user.setRole(adminRole);

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        user.setPasswordHash(hashedPassword);

        userRepository.save(user);

        return new CompanyRegistrationResponse("Company registered successfully");
    }
}