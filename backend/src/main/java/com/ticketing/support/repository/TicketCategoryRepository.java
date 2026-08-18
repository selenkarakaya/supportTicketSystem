package com.ticketing.support.repository;

import com.ticketing.support.entity.TicketCategory;

import org.springframework.data.jpa.repository.JpaRepository;



public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer>{

}