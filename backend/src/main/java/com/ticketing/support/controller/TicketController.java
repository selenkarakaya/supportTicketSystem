package com.ticketing.support.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ticketing.support.dto.TicketResponse;
import com.ticketing.support.service.TicketService;
import com.ticketing.support.dto.CreateTicketRequest;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest request, Authentication authentication){
        TicketResponse response =  ticketService.createTicket(request, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getTickets(
        Authentication authentication
    ) {
        List<TicketResponse> tickets = ticketService.getTickets(
            authentication.getName()
        );

        return ResponseEntity.ok(tickets);
    }
}