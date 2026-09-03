package com.ticketing.support.service;

import com.ticketing.support.dto.UserResponse;
import com.ticketing.support.entity.User;
import com.ticketing.support.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get users from a specific company
    public List<UserResponse> getUsersByCompany(Long companyId) {

        return userRepository
            .findByCompanyId(companyId)
            .stream()
            .map(user -> new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getJobTitle(),
                user.getRole().getName(),
                user.getDepartment() != null
                    ? user.getDepartment().getId()
                    : null,
                user.getDepartment() != null
                    ? user.getDepartment().getName()
                    : null,
                user.isActive()
            ))
            .toList();
    }

    // Get logged-in user's company users
    public List<UserResponse> getCompanyUsers(String email) {

        User currentUser = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found.")
            );

        Long companyId = currentUser
            .getCompany()
            .getId();

        return getUsersByCompany(companyId);
    }


    // get users by department

    // Get active users from a specific department
    public List<UserResponse> getUsersByDepartment(
        String email,
        Long departmentId
    ) {

    User currentUser = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                    new IllegalArgumentException("User not found.")
            );

    Long companyId = currentUser
            .getCompany()
            .getId();

    List<User> departmentUsers =
            userRepository
                    .findByCompanyIdAndDepartmentIdAndIsActiveTrue(
                            companyId,
                            departmentId
                    );

    return departmentUsers.stream()
            .map(user -> new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getJobTitle(),
                    user.getRole().getName(),
                    user.getDepartment().getId(),
                    user.getDepartment().getName(),
                    user.isActive()
            ))
            .toList();
    }
}