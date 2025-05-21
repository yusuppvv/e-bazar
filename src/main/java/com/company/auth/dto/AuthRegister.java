package com.company.auth.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthRegister {
    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    @NotBlank
    private String email;
}
