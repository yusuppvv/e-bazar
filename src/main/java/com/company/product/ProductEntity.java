package com.company.product;

import java.util.UUID;


import com.company.component.BaseMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
public class ProductEntity extends BaseMapper{
    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "price" , nullable = false)
    private double price;

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

    public ProductEntity() {

    }
}
