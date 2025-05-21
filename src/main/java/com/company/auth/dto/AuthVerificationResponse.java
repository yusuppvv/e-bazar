package com.company.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AuthVerificationResponse {
    private UUID userId;
    private String fullName;
    private String email;
}
