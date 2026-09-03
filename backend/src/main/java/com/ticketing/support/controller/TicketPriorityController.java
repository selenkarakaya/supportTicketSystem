package com.ticketing.support.controller;

import com.ticketing.support.dto.TicketPriorityResponse;
import com.ticketing.support.service.TicketPriorityService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket-priorities")
public class TicketPriorityController{

    private final TicketPriorityService ticketPriorityService;
    @GetMapping
    public List<TicketPriorityResponse> getTicketPriorities(){
        return ticketPriorityService.getTicketPriorities();
    }
}
