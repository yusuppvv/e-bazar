package com.company.location;

import com.company.component.ApiResponse;
import com.company.component.Components;
import com.company.location.dto.LocationCreation;
import com.company.location.dto.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public ApiResponse<?> create(LocationCreation locationCreation) {
        Optional<LocationEntity> optionalLocation = locationRepository.findByUserIdAndVisibilityTrue(locationCreation.getUserId());
        if (optionalLocation.isPresent()) {
            return new ApiResponse<>
                    (404, Components.LOCATION_ALREADY_EXISTS);
        }
        else {
            LocationEntity save = locationRepository.save(toEntity(locationCreation));
            return new ApiResponse<>(200, toResponse(save));
        }
    }

    public ApiResponse<?> get(UUID userId) {
        Optional<LocationEntity> optionalLocation = locationRepository.findByUserIdAndVisibilityTrue(userId);
        if (optionalLocation.isPresent()) {
            return new ApiResponse<>(200, toResponse(optionalLocation.get()));
        }
        else {
            return new ApiResponse<>(400, Components.LOCATION_IS_NULL);
        }
    }

    public ApiResponse<?> update(UUID userId, LocationCreation locationCreation) {
        Optional<LocationEntity> optionalLocation = locationRepository.findByUserIdAndVisibilityTrue(userId);
        if (optionalLocation.isPresent()) {
            LocationEntity locationEntity = optionalLocation.get();
            locationEntity.setAddressLine(locationCreation.getAddressLine());
            locationEntity.setAddressLine1(locationCreation.getAddressLine1());
            locationEntity.setCity(locationCreation.getCity());
            return new ApiResponse<>(200, toResponse(locationRepository.save(locationEntity)));
        }
        else {
            return new ApiResponse<>(404, Components.LOCATION_IS_NULL);
        }
     }

    public ApiResponse<String> delete(UUID userId) {
        Optional<LocationEntity> optionalLocation = locationRepository.findByUserIdAndVisibilityTrue(userId);
        if (optionalLocation.isPresent()) {
            optionalLocation.get().setVisibility(false);
            return new ApiResponse<>(200, Components.DELETED);
        }
        else {
            return new ApiResponse<>(404, Components.NOT_FOUND);
        }
    }
    private LocationResponse toResponse(LocationEntity locationEntity) {
        return new LocationResponse(locationEntity.getUserId(), locationEntity.getAddressLine(), locationEntity.getAddressLine1(),locationEntity.getCity());
    }

    private LocationEntity toEntity(LocationCreation locationCreation) {
        return new LocationEntity(locationCreation.getUserId(), locationCreation.getAddressLine(), locationCreation.getAddressLine1(), locationCreation.getCity());
    }
}
