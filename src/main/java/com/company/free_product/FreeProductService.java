package com.company.free_product;

import com.company.component.ApiResponse;
import com.company.component.Components;
import com.company.free_product.dto.FreeProductCreation;
import com.company.free_product.dto.FreeProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FreeProductService {
    private final FreeProductRepository freeProductRepository;

    public ApiResponse<FreeProductResponse> create(FreeProductCreation freeProductCreation) {
        try {
            FreeProductEntity save = freeProductRepository.save(toEntity(freeProductCreation));
            return new ApiResponse<>(200, toResponse(save));
        } catch (Exception e) {
            return new ApiResponse<>(404, Components.ERROR);
        }
    }

    public ApiResponse<Page<FreeProductResponse>> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<FreeProductResponse> list = freeProductRepository.findAllByVisibilityTrue(pageable)
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
    public ApiResponse<Page<FreeProductResponse>> search(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<FreeProductResponse> list = freeProductRepository.findAllByTitleAndVisibilityTrue(title, pageable).stream().map(this::toResponse).toList();
        if (list.isEmpty()) {
            return new ApiResponse<>(404, Components.ERROR);
        }
        else {
            return new ApiResponse<>(200, new PageImpl<>(list, pageable, list.size()));
        }
    }

    public ApiResponse<Page<FreeProductResponse>> getByCategoryId(UUID categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Components.CREATED_AT));
        List<FreeProductResponse> list = freeProductRepository.findAllByCategoryIdAndVisibilityTrue(categoryId, pageable).stream().map(this::toResponse).toList();
        if (list.isEmpty()) {
            return new ApiResponse<>(404, Components.ERROR);
        }
        else {
            return new ApiResponse<>(200, new PageImpl<>(list, pageable, list.size()));
        }
    }

    public ApiResponse<FreeProductResponse> getByProductId(UUID productId) {
        Optional<FreeProductEntity> optional = freeProductRepository.findByIdAndVisibilityTrue(productId);
        if (optional.isPresent()) {
            return new ApiResponse<>(toResponse(optional.get()));
        }
        else {
            return new ApiResponse<>(404, Components.ERROR);
        }
    }

    public ApiResponse<FreeProductResponse> update(UUID userId, UUID productId, FreeProductCreation freeProductCreation) {
        Optional<FreeProductEntity> optionalProduct = freeProductRepository.findByUserIdAndIdAndVisibilityTrue(userId, productId);
        if (optionalProduct.isPresent()) {
            FreeProductEntity freeProductEntity = optionalProduct.get();
            freeProductEntity.setTitle(freeProductCreation.getTitle());
            freeProductEntity.setDescription(freeProductCreation.getDescription());
            freeProductEntity.setContact(freeProductCreation.getContact());
            freeProductEntity.setCategoryId(freeProductCreation.getCategory_id());
            freeProductEntity.setLocationId(freeProductCreation.getLocation_id());
            freeProductEntity.setUserId(freeProductCreation.getUser_id());
            FreeProductEntity saved = freeProductRepository.save(freeProductEntity);
            return new ApiResponse<>(200, toResponse(saved));
        }
        else {
            return new ApiResponse<>(400, "You have no product to update");
        }
    }

    public ApiResponse<String> delete(UUID productId, UUID userId) {
        Optional<FreeProductEntity> optionalProduct = freeProductRepository.findByIdAndUserIdAndVisibilityTrue(productId, userId);
        if (optionalProduct.isPresent()) {
            FreeProductEntity freeProductEntity = optionalProduct.get();
            freeProductEntity.setVisibility(false);
            freeProductRepository.save(freeProductEntity);
            return new ApiResponse<>(200, "Successfully deleted");
        }
        else {
            return new ApiResponse<>(404, "You have no product to delete.");
        }
    }

    private FreeProductResponse toResponse(FreeProductEntity entity) {
        return new FreeProductResponse(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getContact(), entity.getCategoryId(), entity.getLocationId(), entity.getUserId());
    }

    private FreeProductEntity toEntity(FreeProductCreation productCreation) {
        return new FreeProductEntity(productCreation.getTitle(), productCreation.getDescription(), productCreation.getContact(), productCreation.getCategory_id(), productCreation.getLocation_id(), productCreation.getUser_id());
    }
}
