package com.ticketing.support.repository;

import com.ticketing.support.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long>{

    Optional<Ticket> findByIdAndCompanyId(
        Long id,
        Long companyId
    );

    List<Ticket> findByCompanyIdOrderByCreatedAtDesc(Long companyId);
}