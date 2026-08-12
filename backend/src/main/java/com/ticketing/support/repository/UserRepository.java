package com.ticketing.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticketing.support.entity.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}