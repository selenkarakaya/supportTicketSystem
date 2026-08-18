package com.ticketing.support.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CreateTicketRequest {

    @NotBlank
    @Size(max = 200)
    private String subject;

    @NotBlank
    private String description;

    @NotNull
    private Integer priorityId;

    private Long departmentId;

    @NotNull
    private Integer categoryId;
}