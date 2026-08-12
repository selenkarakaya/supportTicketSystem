package com.ticketing.support.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ticketing.support.entity.Role;
import java.util.Optional;

public interface RoleRepository
        extends JpaRepository<Role, Integer> {
                Optional<Role> findByName(String name);
}