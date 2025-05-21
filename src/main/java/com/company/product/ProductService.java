package com.company.product;

import com.company.component.Components;
import com.company.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;

import com.company.component.ApiResponse;
import com.company.product.dto.ProductCreation;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ApiResponse<ProductResponse> create(ProductCreation productCreation) {
        try {
            ProductEntity save = productRepository.save(toEntity(productCreation));
            return new ApiResponse<>(200, toResponse(save));
        } catch (Exception e) {
            return new ApiResponse<>(404, Components.ERROR);
        }
    }

    public ApiResponse<Page<ProductResponse>> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<ProductResponse> list = productRepository.findAllByVisibilityTrue(pageable)
                .stream()
                .map(this::toResponse)
                .toList();
        if (list.isEmpty()) {
            return new ApiResponse<>(404, Components.ERROR);
        }
        else {
            return new ApiResponse<>(200, new PageImpl<>(list, pageable, list.size()));
        }
    }
    public ApiResponse<Page<ProductResponse>> search(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<ProductResponse> list = productRepository.findAllByTitleAndVisibilityTrue(name).stream().map(this::toResponse).toList();
        if (list.isEmpty()) {
            return new ApiResponse<>(404, Components.ERROR);
        }
        else {
            return new ApiResponse<>(200, new PageImpl<>(list, pageable, list.size()));
        }
    }

    public ApiResponse<Page<ProductResponse>> searchByCategory(UUID id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<ProductResponse> list = productRepository.findAllByCategoryIdAndVisibilityTrue(id).stream().map(this::toResponse).toList();
        if (list.isEmpty()) {
            return new ApiResponse<>(404, Components.ERROR);
        }
        else {
            return new ApiResponse<>(200, new PageImpl<>(list, pageable, list.size()));
        }
    }

    public ApiResponse<ProductResponse> getByProductId(UUID productId) {
        Optional<ProductEntity> optional = productRepository.findByIdAndVisibilityTrue(productId);
        if (optional.isPresent()) {
            ProductResponse productResponse = toResponse(optional.get());
            return new ApiResponse<>(200, productResponse);
        } else {
            return new ApiResponse<>(404, Components.ERROR);
        }
    }

    public ApiResponse<ProductResponse> update(UUID userId, UUID productId, ProductCreation productCreation) {
        Optional<ProductEntity> optionalProduct = productRepository.findByUserIdAndIdAndVisibilityTrue(userId, productId);
        if (optionalProduct.isPresent()) {
            ProductEntity productEntity = getProduct(productCreation, optionalProduct);
            ProductEntity saved = productRepository.save(productEntity);
            return new ApiResponse<>(200, toResponse(saved));
        }
        else {
            return new ApiResponse<>(400, "You have no product to update");
        }
    }

    private static ProductEntity getProduct(ProductCreation productCreation, Optional<ProductEntity> optionalProduct) {
        ProductEntity productEntity = optionalProduct.get();
        productEntity.setTitle(productCreation.getTitle());
        productEntity.setPrice(productCreation.getPrice());
        productEntity.setDescription(productCreation.getDescription());
        productEntity.setContact(productCreation.getContact());
        productEntity.setCategoryId(productCreation.getCategory_id());
        productEntity.setLocationId(productCreation.getLocation_id());
        productEntity.setUserId(productCreation.getUser_id());
        return productEntity;
    }

    public ApiResponse<String> delete(UUID productId, UUID userId) {
        Optional<ProductEntity> optionalProduct = productRepository.findByIdAndUserIdAndVisibilityTrue(productId, userId);
        if (optionalProduct.isPresent()) {
            ProductEntity productEntity = optionalProduct.get();
            productEntity.setVisibility(false);
            productRepository.save(productEntity);
            return new ApiResponse<>(200, "Successfully deleted");
        }
        else {
            return new ApiResponse<>(404, "You have no product to delete.");
        }
    }

    private ProductResponse toResponse(ProductEntity entity) {
        return new ProductResponse(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getDescription(), entity.getContact(), entity.getCategoryId(), entity.getLocationId(), entity.getUserId());
    }

    private ProductEntity toEntity(ProductCreation productCreation) {
        return new ProductEntity(productCreation.getTitle(), productCreation.getPrice(), productCreation.getDescription(), productCreation.getContact(), productCreation.getCategory_id(), productCreation.getLocation_id(), productCreation.getUser_id());
    }
}
