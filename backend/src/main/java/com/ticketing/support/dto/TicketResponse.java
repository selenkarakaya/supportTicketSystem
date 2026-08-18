package com.ticketing.support.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String ticketNumber;
    private String subject;
    private String description;
    private Integer statusId;
    private String statusLabel;
    private Integer priorityId;
    private String priorityLabel;
    private Integer categoryId;
    private String categoryLabel;
    private Long departmentId;
    private String departmentName;
    private Long createdByUserId;
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private LocalDateTime closedAt;
    private String message;

}
