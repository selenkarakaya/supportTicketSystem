package com.ticketing.support.controller;

import com.ticketing.support.dto.InvitationResponse;
import com.ticketing.support.dto.InvitedUserRegistrationRequest;
import com.ticketing.support.dto.InvitedUserRegistrationResponse;
import com.ticketing.support.service.InvitationService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(
            InvitationService invitationService
    ) {
        this.invitationService = invitationService;
    }

    @PostMapping("/{role}")
    public InvitationResponse createInvitation(
            @PathVariable String role,
            Authentication authentication
    ) {
        return invitationService.createInvitation(
                authentication.getName(),
                role
        );
    }

    @GetMapping("/validate")
    public InvitationResponse validateInvitation(
            @RequestParam String token
    ) {
        return invitationService.validateInvitation(token);
    }

    @PostMapping("/register")
    public InvitedUserRegistrationResponse registerInvitedUser(
            @RequestBody InvitedUserRegistrationRequest request
    ) {
        return invitationService.registerInvitedUser(request);
    }
}