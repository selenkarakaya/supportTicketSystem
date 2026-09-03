package com.ticketing.support.service;

import com.ticketing.support.dto.TicketPriorityResponse;
import com.ticketing.support.entity.TicketPriority;
import com.ticketing.support.repository.TicketPriorityRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketPriorityService {

    private final TicketPriorityRepository ticketPriorityRepository;

    public List<TicketPriorityResponse> getTicketPriorities() {

        List<TicketPriority> priorities =
                ticketPriorityRepository.findAll();

        return priorities.stream()
                .map(priority -> new TicketPriorityResponse(
                        priority.getId(),
                        priority.getName(),
                        priority.getLabel(),
                        priority.getDescription()
                ))
                .toList();
    }
}