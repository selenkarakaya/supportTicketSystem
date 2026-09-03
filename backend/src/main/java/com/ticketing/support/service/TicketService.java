package com.ticketing.support.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


import com.ticketing.support.dto.CreateTicketRequest;
import com.ticketing.support.dto.TicketResponse;
import com.ticketing.support.entity.User;
import com.ticketing.support.entity.TicketPriority;
import com.ticketing.support.entity.TicketCategory;
import com.ticketing.support.entity.TicketStatus;
import com.ticketing.support.entity.Department;

import com.ticketing.support.entity.Ticket;
import com.ticketing.support.repository.TicketRepository;
import com.ticketing.support.repository.UserRepository;
import com.ticketing.support.repository.TicketCategoryRepository;
import com.ticketing.support.repository.TicketPriorityRepository;
import com.ticketing.support.repository.TicketStatusRepository;
import com.ticketing.support.repository.DepartmentRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketPriorityRepository ticketPriorityRepository;
    private final TicketStatusRepository ticketStatusRepository;
    private final DepartmentRepository departmentRepository;

    private String generateTicketNumber() {
        String date = LocalDate.now()
            .format(DateTimeFormatter.BASIC_ISO_DATE);
    
        String randomPart = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 12)
            .toUpperCase(Locale.ROOT);
    
        return "TKT-" + date + "-" + randomPart;
    }

    @Transactional
    public TicketResponse createTicket(CreateTicketRequest request, String email){

        /*  Find the existing entity using the ID received from the DTO
            and assign the result to a variable.

            DataType variableName = value;
            EntityType variableName = repository
                                    .findById(request.getEntityId())
                                    .orElseThrow(...);
            Check the dto's field names to determine the corresponding getter and setter method names.
        */ 
        User currentUser = userRepository.findByEmail(email)
                            .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
        

        TicketPriority priority = ticketPriorityRepository.findById(request.getPriorityId())
                                    .orElseThrow(() -> new IllegalArgumentException("Priority not found"));

        TicketCategory category = ticketCategoryRepository.findById(request.getCategoryId())
                                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        TicketStatus status = ticketStatusRepository.findByName("open")
                                .orElseThrow(() -> new IllegalStateException("Open ticket status not found"));

        Department department = null;
        
        if (request.getDepartmentId() != null) {
            department = departmentRepository
            .findByIdAndCompanyId( request.getDepartmentId(), currentUser.getCompany().getId())
            .orElseThrow(() -> new IllegalArgumentException( "Department not found"));
        }
        
        /*
        
        Create a new Ticket entity and set its fields because this entity will be saved as a new record in the database.
        Check the entity's field names to determine the corresponding getter and setter method names.
        */

        Ticket ticket = new Ticket();

        ticket.setSubject(request.getSubject().trim());
        ticket.setDescription(request.getDescription().trim());
        ticket.setPriority(priority);
        ticket.setCategory(category);
        ticket.setStatus(status);
        ticket.setDepartment(department);
        ticket.setCreatedByUser(currentUser);
        ticket.setCompany(currentUser.getCompany());
        ticket.setTicketNumber(generateTicketNumber());
        
        Ticket savedTicket = ticketRepository.save(ticket);

        Long departmentId = null;
        String departmentName = null;

        if (savedTicket.getDepartment() != null) {
            departmentId = savedTicket.getDepartment().getId();
            departmentName = savedTicket.getDepartment().getName();
        }

        TicketResponse response = TicketResponse.builder()
                                    .id(savedTicket.getId())
                                    .ticketNumber(savedTicket.getTicketNumber())
                                    .subject(savedTicket.getSubject())
                                    .description(savedTicket.getDescription())
                                    .statusId(savedTicket.getStatus().getId())
                                    .statusLabel(savedTicket.getStatus().getLabel())
                                    .priorityId(savedTicket.getPriority().getId())
                                    .priorityLabel(savedTicket.getPriority().getLabel())
                                    .categoryId(savedTicket.getCategory().getId())
                                    .categoryLabel(savedTicket.getCategory().getLabel())
                                    .departmentId(departmentId)
                                    .departmentName(departmentName)
                                    .createdByUserId(savedTicket.getCreatedByUser().getId())
                                    .companyId(savedTicket.getCompany().getId())
                                    .createdAt(savedTicket.getCreatedAt())
                                    .updatedAt(savedTicket.getUpdatedAt())
                                    .resolvedAt(savedTicket.getResolvedAt())
                                    .closedAt(savedTicket.getClosedAt())
                                    .message("Ticket created successfully")
                                    .build();

                                    
        return response;
    }

    @Transactional(readOnly = true)
public List<TicketResponse> getTickets(String email) {

    User currentUser = userRepository
        .findByEmail(email)
        .orElseThrow(() ->
            new IllegalStateException("Authenticated user not found")
        );

    Long companyId = currentUser.getCompany().getId();
    Long userId = currentUser.getId();

    String roleName = currentUser
        .getRole()
        .getName()
        .trim()
        .toUpperCase(Locale.ROOT);

    List<Ticket> tickets;

    switch (roleName) {
        case "COMPANY_ADMIN" ->
            tickets = ticketRepository
                .findByCompanyIdOrderByCreatedAtDesc(companyId);

        case "SUPPORT_AGENT" -> {
            Long departmentId = currentUser.getDepartment() != null
                ? currentUser.getDepartment().getId()
                : null;

            tickets = ticketRepository
                .findVisibleTicketsForSupportAgent(
                    companyId,
                    userId,
                    departmentId
                );
        }

        case "EMPLOYEE" ->
            tickets = ticketRepository
                .findByCompanyIdAndCreatedByUserIdOrderByCreatedAtDesc(
                    companyId,
                    userId
                );

        default -> throw new IllegalStateException(
            "Unsupported user role: " + roleName
        );
    }

    return tickets.stream()
        .map(this::mapToTicketResponse)
        .toList();
}

private TicketResponse mapToTicketResponse(Ticket ticket) {

    Long departmentId = null;
    String departmentName = null;

    if (ticket.getDepartment() != null) {
        departmentId = ticket.getDepartment().getId();
        departmentName = ticket.getDepartment().getName();
    }

    return TicketResponse.builder()
        .id(ticket.getId())
        .ticketNumber(ticket.getTicketNumber())
        .subject(ticket.getSubject())
        .description(ticket.getDescription())
        .statusId(ticket.getStatus().getId())
        .statusLabel(ticket.getStatus().getLabel())
        .priorityId(ticket.getPriority().getId())
        .priorityLabel(ticket.getPriority().getLabel())
        .categoryId(ticket.getCategory().getId())
        .categoryLabel(ticket.getCategory().getLabel())
        .departmentId(departmentId)
        .departmentName(departmentName)
        .createdByUserId(ticket.getCreatedByUser().getId())
        .companyId(ticket.getCompany().getId())
        .createdAt(ticket.getCreatedAt())
        .updatedAt(ticket.getUpdatedAt())
        .resolvedAt(ticket.getResolvedAt())
        .closedAt(ticket.getClosedAt())
        .build();
}
}