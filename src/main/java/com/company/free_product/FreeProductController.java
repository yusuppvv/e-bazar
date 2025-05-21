package com.company.free_product;

import com.company.component.ApiResponse;
import com.company.free_product.dto.FreeProductCreation;
import com.company.free_product.dto.FreeProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/e-free")
public class FreeProductController {
    private final FreeProductService freeProductService;

    public FreeProductController(FreeProductService freeProductService) {
        this.freeProductService = freeProductService;
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody FreeProductCreation freeProductCreation) {
        return ResponseEntity.ok(freeProductService.create(freeProductCreation));
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<Page<FreeProductResponse>>> get(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(freeProductService.get(page, size));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<ApiResponse<Page<FreeProductResponse>>> search(@PathVariable String title,
                                                                         @RequestParam (defaultValue = "0") int page,
                                                                         @RequestParam (defaultValue = "10") int size) {
        return ResponseEntity.ok(freeProductService.search(title, page, size));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ApiResponse<FreeProductResponse>> getByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok(freeProductService.getByProductId(productId));
    }

    @GetMapping("/get/{categoryId]")
    public ResponseEntity<ApiResponse<Page<FreeProductResponse>>> getByCategoryId(@PathVariable UUID categoryId,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(freeProductService.getByCategoryId(categoryId, page, size));

    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID productId, @RequestParam UUID userId) {
        return ResponseEntity.ok(freeProductService.delete(productId, userId));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<FreeProductResponse>> update(@RequestParam UUID userId,
                                                                   @RequestParam UUID productId,
                                                                   @RequestBody FreeProductCreation freeProductCreation
                                                               ) {
        return ResponseEntity.ok(freeProductService.update(userId, productId, freeProductCreation));
    }



}
