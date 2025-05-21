package com.company.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private String fullName;
    private String email;
    private LocalDateTime createdAt;
    private UUID locationId;
}
