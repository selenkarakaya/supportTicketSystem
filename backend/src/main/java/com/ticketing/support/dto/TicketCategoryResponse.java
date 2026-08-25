package com.ticketing.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketCategoryResponse {

    private Integer id;
    private String name;
    private String label;
    private String description;
}