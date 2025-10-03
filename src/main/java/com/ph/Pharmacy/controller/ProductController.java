package com.ph.Pharmacy.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // ========== UPDATED: Changed @RequestParam to @RequestPart for text fields ========== //
    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestPart("productData") String productDataJson,
            @RequestPart("mainImage") MultipartFile mainImage,
            @RequestPart(value = "subImages", required = false) List<MultipartFile> subImages) throws Exception {

        log.info("Request received to create product");  // ADDED: Logging

        // Parse JSON string to DTO
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequestDto requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);

        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);

        ProductResponseDto responseDto = productService.createProduct(requestDto);
        log.info("Product created successfully with ID: {}", responseDto.getId());
        return ResponseEntity.ok(responseDto);
    }
    // ========== END UPDATED ========== //



    // ========== get product by product id ========== //

    @GetMapping("/get-product/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        log.info("Request received to get product by ID: {}", id);
        ProductResponseDto responseDto = productService.getProduct(id);
        log.info("Product retrieved successfully with ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    // ========== UPDATED: Added pagination ========== //
    @GetMapping("/get-all-products")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to get all products - page: {}, size: {}", page, size);  // ADDED: Logging
        Page<ProductResponseDto> responseDtos = productService.getAllProducts(page, size);
        log.info("Retrieved {} products on page {}", responseDtos.getNumberOfElements(), page);  // ADDED: Logging
        return ResponseEntity.ok(responseDtos);
    }
    // ========== END UPDATED ========== //



    // ========== ADDED: New endpoint for get by category ========== //
    @GetMapping("/get-by-category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String category) {
        log.info("Request received to get products by category: {}", category);
        List<ProductResponseDto> responseDtos = productService.getProductsByCategory(category);
        log.info("Retrieved {} products for category: {}", responseDtos.size(), category);
        return ResponseEntity.ok(responseDtos);
    }
    // ========== END ADDED ========== //


    // ========== UPDATED: Changed @RequestParam to @RequestPart for PUT ========== //
    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestPart("productData") String productDataJson,
            @RequestPart("mainImage") MultipartFile mainImage,
            @RequestPart(value = "subImages", required = false) List<MultipartFile> subImages) throws Exception {

        log.info("Request received to update product with ID: {}", id);

        // Parse JSON string to DTO
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequestDto requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);

        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);

        ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
        log.info("Product updated successfully with ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }
    // ========== END UPDATED ==========//


    // ========== UPDATED: Changed @RequestParam to @RequestPart for PATCH ========== //
    @PatchMapping("/patch-product/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable Long id,
            @RequestPart(value = "productData", required = false) String productDataJson,  // All text fields as JSON (optional)
            @RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestPart(value = "subImages", required = false) List<MultipartFile> subImages) throws Exception {

        log.info("Request received to patch product with ID: {}", id);  // Logging

        ProductRequestDto requestDto = new ProductRequestDto();

        // Parse JSON string to DTO if provided
        if (productDataJson != null && !productDataJson.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);
        }

        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);

        ProductResponseDto responseDto = productService.patchProduct(id, requestDto);
        log.info("Product patched successfully with ID: {}", id);  // Logging
        return ResponseEntity.ok(responseDto);
    }
    // ========== END UPDATED ==========//


    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Request received to delete product with ID: {}", id);  // ADDED: Logging
        productService.deleteProduct(id);
        log.info("Product deleted successfully with ID: {}", id);  // ADDED: Logging
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        log.debug("Request received to get main image for product ID: {}", productId);  // ADDED: Logging
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && product.get().getMainImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getMainImage());
        }
        log.warn("Main image not found for product ID: {}", productId);  // ADDED: Logging
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}/subimage/{index}")
    public ResponseEntity<byte[]> getProductSubImage(@PathVariable Long productId, @PathVariable int index) {
        log.debug("Request received to get sub image {} for product ID: {}", index, productId);  // ADDED: Logging
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && index < product.get().getSubImages().size()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getSubImages().get(index));
        }
        log.warn("Sub image {} not found for product ID: {}", index, productId);  // ADDED: Logging
        return ResponseEntity.notFound().build();
    }
}