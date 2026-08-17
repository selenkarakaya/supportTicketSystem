package com.ticketing.support.dto;

public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String jobTitle;
    private String role;
    private Long departmentId;
    private String departmentName;
    private boolean active;

    public UserResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String jobTitle,
        String role,
        Long departmentId,
        String departmentName,
        boolean active
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.role = role;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.active = active;
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

    public String getPhone() {
        return phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getRole() {
        return role;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public boolean isActive() {
        return active;
    }
}