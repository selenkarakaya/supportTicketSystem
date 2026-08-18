package com.ticketing.support.repository;

import com.ticketing.support.entity.TicketStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer>{

    Optional<TicketStatus> findByName(String name);
}