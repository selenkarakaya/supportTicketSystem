package com.ticketing.support.dto;

public class InvitationResponse {

    private String message;
    private String invitationLink;
    private Boolean valid;
    private Long companyId;
    private String companyName;
    private String role;

    public InvitationResponse(
            String message,
            String invitationLink,
            Boolean valid,
            Long companyId,
            String companyName,
            String role
    ) {
        this.message = message;
        this.invitationLink = invitationLink;
        this.valid = valid;
        this.companyId = companyId;
        this.companyName = companyName;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public Boolean getValid() {
        return valid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }
}