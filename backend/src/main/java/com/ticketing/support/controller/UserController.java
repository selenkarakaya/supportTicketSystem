package com.ticketing.support.controller;

import com.ticketing.support.dto.UserResponse;
import com.ticketing.support.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getCompanyUsers(
        Authentication authentication
    ) {
        return userService.getCompanyUsers(
            authentication.getName()
        );
    }
}