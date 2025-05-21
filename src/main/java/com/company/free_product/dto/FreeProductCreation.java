package com.company.free_product.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FreeProductCreation {
    @NotBlank
    @NonNull
    private String title;
    @NotBlank
    @NonNull
    private String description;
    @NotBlank
    @NonNull
    private String contact;
    @NotBlank
    @NonNull
    private UUID category_id;
    @NotBlank
    @NonNull
    private UUID location_id;
    @NotBlank
    @NonNull
    private UUID user_id;
}
