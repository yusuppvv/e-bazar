package com.company.free_product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FreeProductRepository extends JpaRepository<FreeProductEntity, UUID> {
    List<FreeProductEntity> findAllByVisibilityTrue(Pageable pageable);

    Optional<FreeProductEntity> findByUserIdAndIdAndVisibilityTrue(UUID userId, UUID productId);

    Optional<FreeProductEntity> findByIdAndUserIdAndVisibilityTrue(UUID productId, UUID userId);
}
