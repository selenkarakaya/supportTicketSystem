package com.ticketing.support.service;

import com.ticketing.support.dto.TicketCategoryResponse;
import com.ticketing.support.entity.TicketCategory;
import com.ticketing.support.repository.TicketCategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    public List<TicketCategoryResponse> getActiveCategories() {
        List<TicketCategory> categories =
            ticketCategoryRepository.findByIsActiveTrue();

        return categories.stream()
            .map(category ->
                new TicketCategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getLabel(),
                    category.getDescription()
                )
            )
            .toList();
    }
}