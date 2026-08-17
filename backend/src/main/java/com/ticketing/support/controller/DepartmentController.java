package com.ticketing.support.controller;

import com.ticketing.support.dto.DepartmentResponse;
import com.ticketing.support.service.DepartmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(
            DepartmentService departmentService
    ) {
        this.departmentService = departmentService;
    }

    @GetMapping("/company/{companyId}")
    public List<DepartmentResponse> getDepartmentsByCompany(
            @PathVariable Long companyId
    ) {
        return departmentService.getDepartmentsByCompany(companyId);
    }
}