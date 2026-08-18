package com.ticketing.support.repository;

import com.ticketing.support.entity.TicketPriority;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface TicketPriorityRepository extends JpaRepository<TicketPriority, Integer>{


}