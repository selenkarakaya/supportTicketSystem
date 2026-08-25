package com.ticketing.support.repository;

import com.ticketing.support.entity.TicketCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer>{

    List<TicketCategory> findByIsActiveTrue();

}
