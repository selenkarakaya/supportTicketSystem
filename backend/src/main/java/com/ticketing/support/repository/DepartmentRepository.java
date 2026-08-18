package com.ticketing.support.repository;

import com.ticketing.support.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    List<Department> findByCompanyId(Long companyId);
    Optional<Department> findByIdAndCompanyId(
        Long id,
        Long companyId
    );
}