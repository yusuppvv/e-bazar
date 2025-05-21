package com.company.location;

import com.company.component.BaseMapper;

import com.company.component.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "location")
@Data
@Builder
@AllArgsConstructor
public class LocationEntity extends BaseMapper{
    @Column(name = "user_id", unique = true)
    private UUID userId;
    @Column(name = "address_line")
    private String addressLine;
    
    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private City city;
    public LocationEntity() {

    }
}
