package com.company.photo;


import com.company.component.ApiResponse;
import com.company.photo.dto.PhotoResp;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<PhotoResp>> upload(@RequestParam MultipartFile file,
                                                         @RequestParam UUID productId) {
        return ResponseEntity.ok(photoService.upload(file, productId));
    }

    @PostMapping("/multi-upload")
    public ResponseEntity<ApiResponse<List<PhotoResp>>> multiUpload(@RequestParam("files") MultipartFile [] files,
                                                       @RequestParam UUID productId) {
        return ResponseEntity.ok(
                photoService.multiUpload(files, productId)
        );

    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<ApiResponse<PhotoResp>> metadata(@PathVariable UUID id) {
        return ResponseEntity.ok(photoService.metadata(id));
    }

    //Delete by id
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(photoService.deleteById(id));
    }

    @GetMapping(value = "/get-photo-by-id/{id}",
    produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                photoService.getById(id)
        );
    }

    @GetMapping("/get-by-product-id/{productId}")
    public ResponseEntity<ApiResponse<List<PhotoResp>>> getPhotoByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok(photoService.getPhotosByProductId(productId));
    }

    @GetMapping(value = "/search",
    produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @PermitAll
    public ResponseEntity<byte[]> searchPhotoByName(@RequestParam String name) {
        return ResponseEntity.ok(photoService.searchPhotosByName(name));
    }
}
