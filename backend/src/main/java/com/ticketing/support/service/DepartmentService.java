package com.ticketing.support.service;

import com.ticketing.support.dto.DepartmentResponse;
import com.ticketing.support.entity.Department;
import com.ticketing.support.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository
    ) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponse> getDepartmentsByCompany(
            Long companyId
    ) {

        List<Department> departments =
                departmentRepository.findByCompanyId(companyId);

        return departments.stream()
                .map(department ->
                        new DepartmentResponse(
                                department.getId(),
                                department.getName(),
                                department.getDescription()
                        )
                )
                .toList();
    }
}