package com.ticketing.support.dto;

public class AuthResponse {

    private String message;
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private Long companyId;
    private String companyName;
    private Long departmentId;
    private String departmentName;

    public AuthResponse(
        String message,
        Long id,
        String fullName,
        String email,
        String role,
        Long companyId,
        String companyName,
        Long departmentId,
        String departmentName
) {
    this.message = message;
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.role = role;
    this.companyId = companyId;
    this.companyName = companyName;
    this.departmentId = departmentId;
    this.departmentName = departmentName;
}

    
    
    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
}