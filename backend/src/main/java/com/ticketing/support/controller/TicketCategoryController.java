package com.ticketing.support.controller;

import com.ticketing.support.dto.TicketCategoryResponse;
import com.ticketing.support.service.TicketCategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket-categories")
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    @GetMapping
    public List<TicketCategoryResponse> getActiveCategories() {
        return ticketCategoryService.getActiveCategories();
    }
}