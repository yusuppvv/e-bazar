package com.company.location.dto;

import com.company.component.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class LocationResponse {
    private UUID userId;
    private String addressLine;
    private String addressLine1;
    private City city;
}
