package com.company.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAllByVisibilityTrue(Pageable pageable);

    Optional<ProductEntity> findByUserIdAndIdAndVisibilityTrue(UUID userId, UUID productId);

    Optional<ProductEntity> findByIdAndUserIdAndVisibilityTrue(UUID productId, UUID userId);

    List<ProductEntity> findAllByTitleAndVisibilityTrue(String name);

    List<ProductEntity> findAllByCategoryIdAndVisibilityTrue(UUID id);

    Optional<ProductEntity> findByIdAndVisibilityTrue(UUID productId);
}
