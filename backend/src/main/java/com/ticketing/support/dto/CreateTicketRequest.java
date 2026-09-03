package com.ticketing.support.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private Integer categoryId;

    private Long departmentId;

    @NotNull
    private List<Long> assignedUserIds = new ArrayList<>();
}