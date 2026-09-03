package com.ticketing.support.repository;

import com.ticketing.support.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    
    Optional<Ticket> findByIdAndCompanyId(
        Long id,
        Long companyId
    );

    // COMPANY_ADMIN
    List<Ticket> findByCompanyIdOrderByCreatedAtDesc(
        Long companyId
    );

    // EMPLOYEE
    List<Ticket> findByCompanyIdAndCreatedByUserIdOrderByCreatedAtDesc(
        Long companyId,
        Long userId
    );

    // SUPPORT_AGENT
    @Query("""
        SELECT DISTINCT ticket
        FROM Ticket ticket
        WHERE ticket.company.id = :companyId
          AND (
              ticket.createdByUser.id = :userId
              OR ticket.department.id = :departmentId
              OR ticket.department IS NULL
          )
        ORDER BY ticket.createdAt DESC
    """)
    List<Ticket> findVisibleTicketsForSupportAgent(
        @Param("companyId") Long companyId,
        @Param("userId") Long userId,
        @Param("departmentId") Long departmentId
    );
}