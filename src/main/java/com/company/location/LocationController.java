package com.company.location;

import com.company.component.ApiResponse;
import com.company.location.dto.LocationCreation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody LocationCreation locationCreation) {
        return ResponseEntity.ok(locationService.create(locationCreation));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<ApiResponse<?>> get(@PathVariable UUID userId) {
        return ResponseEntity.ok(locationService.get(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable UUID userId, @RequestBody LocationCreation locationCreation) {
        return ResponseEntity.ok(locationService.update(userId, locationCreation));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable UUID userId){
        return ResponseEntity.ok(locationService.delete(userId));
    }
}
