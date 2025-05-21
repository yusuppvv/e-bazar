package com.company.category;

import com.company.component.BaseMapper;
import com.company.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity extends BaseMapper {
    @Column(nullable = false)
    private String name;

}
