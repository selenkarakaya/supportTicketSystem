package com.ticketing.support.repository;

import com.ticketing.support.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByCompanyId(Long companyId);

    List<User> findByCompanyIdAndDepartmentIdAndIsActiveTrue(
            Long companyId,
            Long departmentId
    );
}