package com.ticketing.support.repository;

import com.ticketing.support.entity.TicketPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketPriorityRepository
        extends JpaRepository<TicketPriority, Integer> {

    
}