package com.ticketing.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.support.entity.Company;

public interface CompanyRepository
        extends JpaRepository<Company, Long> {
}