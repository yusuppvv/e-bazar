package com.company.free_product;

import java.util.UUID;

import com.company.component.BaseMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeProductEntity extends BaseMapper{
    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "contact" , nullable = false)
    private String contact;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;
    
    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
