package com.ticketing.support.dto;

public class InvitedUserRegistrationResponse {

    private String message;

    public InvitedUserRegistrationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}