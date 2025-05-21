package com.company.product;

import com.company.component.ApiResponse;
import com.company.product.dto.ProductCreation;
import com.company.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody ProductCreation productCreation) {
        return ResponseEntity.ok(productService.create(productCreation));
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> get(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.get(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> search(
                                                                  @RequestParam(defaultValue = "texnika") String name,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.search(name, page, size));
    }
    @GetMapping("/search-by-category")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> searchByCategory(
                                                                  @RequestParam(defaultValue = "0aae093a-ee3c-40a3-9f22-ddd09b268307") UUID id,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.searchByCategory(id, page, size));
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@RequestParam UUID userId,
                                                               @RequestParam UUID productId,      
                                                               @RequestBody ProductCreation productCreation
                                                               ) {
        return ResponseEntity.ok(productService.update(userId, productId, productCreation));                                                     
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID productId, @RequestParam UUID userId) {
        return ResponseEntity.ok(productService.delete(productId, userId));
    }


}
