package com.ticketing.support.service;

import com.ticketing.support.dto.InvitationResponse;
import com.ticketing.support.entity.Invitation;
import com.ticketing.support.entity.User;
import com.ticketing.support.repository.InvitationRepository;
import com.ticketing.support.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ticketing.support.repository.RoleRepository;
import com.ticketing.support.repository.DepartmentRepository;
import com.ticketing.support.entity.Role;
import com.ticketing.support.entity.Department;
import com.ticketing.support.dto.InvitedUserRegistrationRequest;
import com.ticketing.support.dto.InvitedUserRegistrationResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

public InvitationService(
        InvitationRepository invitationRepository,
        UserRepository userRepository,
        RoleRepository roleRepository,
        DepartmentRepository departmentRepository,
        PasswordEncoder passwordEncoder
) {
    this.invitationRepository = invitationRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.departmentRepository = departmentRepository;
    this.passwordEncoder = passwordEncoder;
}

    public InvitationResponse createInvitation(
            String adminEmail,
            String requestedRole
    ) {

        User admin = userRepository
                .findByEmail(adminEmail)
                .orElseThrow(() ->
                        new IllegalArgumentException("Admin not found.")
                );

        if (!admin.getRole().getName().equals("COMPANY_ADMIN")) {
            throw new IllegalArgumentException(
                    "Only company admins can create invitations."
            );
        }

        String role;

        if (requestedRole.equalsIgnoreCase("employee")) {

            role = "EMPLOYEE";

        } else if (requestedRole.equalsIgnoreCase("support-agent")) {

            role = "SUPPORT_AGENT";

        } else {

            throw new IllegalArgumentException(
                    "Invalid invitation role."
            );
        }

        String token = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();

        invitation.setToken(token);
        invitation.setCompany(admin.getCompany());
        invitation.setRole(role);
        invitation.setUsed(false);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setExpiresAt(
                LocalDateTime.now().plusDays(3)
        );

        invitationRepository.save(invitation);

        String registerPath =
                role.equals("EMPLOYEE")
                        ? "/register-employee"
                        : "/register-support-agent";

        String invitationLink =
                "http://localhost:5173"
                        + registerPath
                        + "?token="
                        + token;

        return new InvitationResponse(
                role.equals("EMPLOYEE")
                        ? "Employee invitation created."
                        : "Support agent invitation created.",
                invitationLink,
                true,
                admin.getCompany().getId(),
                admin.getCompany().getName(),
                role
        );
    }

    public InvitationResponse validateInvitation(String token) {

        Invitation invitation = invitationRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid invitation token."
                        )
                );

        if (invitation.isUsed()) {
            throw new IllegalArgumentException(
                    "This invitation has already been used."
            );
        }

        if (invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "This invitation has expired."
            );
        }

        return new InvitationResponse(
                "Invitation is valid.",
                null,
                true,
                invitation.getCompany().getId(),
                invitation.getCompany().getName(),
                invitation.getRole()
        );
    }

    @Transactional
public InvitedUserRegistrationResponse registerInvitedUser(
        InvitedUserRegistrationRequest request
) {

    Invitation invitation = invitationRepository
            .findByToken(request.getToken())
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Invalid invitation token."
                    )
            );

    if (invitation.isUsed()) {
        throw new IllegalArgumentException(
                "This invitation has already been used."
        );
    }

    if (invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
        throw new IllegalArgumentException(
                "This invitation has expired."
        );
    }

    String email = request.getEmail()
            .trim()
            .toLowerCase();

    if (userRepository.findByEmail(email).isPresent()) {
        throw new IllegalArgumentException(
                "Email is already registered."
        );
    }

    Role role = roleRepository
            .findByName(invitation.getRole())
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Role not found."
                    )
            );

    Department department = departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Department not found."
                    )
            );

    if (!department.getCompany().getId()
            .equals(invitation.getCompany().getId())) {

        throw new IllegalArgumentException(
                "Invalid department."
        );
    }

    User user = new User();

    user.setCompany(invitation.getCompany());
    user.setRole(role);
    user.setDepartment(department);

    user.setFullName(request.getFullName().trim());
    user.setEmail(email);

    user.setPasswordHash(
            passwordEncoder.encode(request.getPassword())
    );

    user.setPhone(request.getPhone());
    user.setJobTitle(request.getJobTitle());
    user.setIsActive(true);

    userRepository.save(user);

    invitation.setUsed(true);
    invitationRepository.save(invitation);

    return new InvitedUserRegistrationResponse(
            "Account created successfully."
    );
}
}