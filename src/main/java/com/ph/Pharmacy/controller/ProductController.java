package com.ph.Pharmacy.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestPart("productData") String productDataJson,
            @RequestPart("productMainImage") MultipartFile productMainImage,  // UPDATED: parameter name
            @RequestPart(value = "productSubImages", required = false) List<MultipartFile> productSubImages) throws Exception {  // UPDATED: parameter name

        log.info("Request received to create product");

        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequestDto requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);

        requestDto.setProductMainImage(productMainImage);  // UPDATED: method name
        requestDto.setProductSubImages(productSubImages);  // UPDATED: method name

        ProductResponseDto responseDto = productService.createProduct(requestDto);
        log.info("Product created successfully with ID: {}", responseDto.getProductId());  // UPDATED: method name
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        log.info("Request received to get product by ID: {}", id);
        ProductResponseDto responseDto = productService.getProduct(id);
        log.info("Product retrieved successfully with ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to get all products - page: {}, size: {}", page, size);
        Page<ProductResponseDto> responseDtos = productService.getAllProducts(page, size);
        log.info("Retrieved {} products on page {}", responseDtos.getNumberOfElements(), page);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String category) {
        log.info("Request received to get products by category: {}", category);
        List<ProductResponseDto> responseDtos = productService.getProductsByCategory(category);
        log.info("Retrieved {} products for category: {}", responseDtos.size(), category);
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestPart("productData") String productDataJson,
            @RequestPart("productMainImage") MultipartFile productMainImage,  // UPDATED: parameter name
            @RequestPart(value = "productSubImages", required = false) List<MultipartFile> productSubImages) throws Exception {  // UPDATED: parameter name

        log.info("Request received to update product with ID: {}", id);

        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequestDto requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);

        requestDto.setProductMainImage(productMainImage);  // UPDATED: method name
        requestDto.setProductSubImages(productSubImages);  // UPDATED: method name

        ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
        log.info("Product updated successfully with ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/patch-product/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable Long id,
            @RequestPart(value = "productData", required = false) String productDataJson,
            @RequestPart(value = "productMainImage", required = false) MultipartFile productMainImage,  // UPDATED: parameter name
            @RequestPart(value = "productSubImages", required = false) List<MultipartFile> productSubImages) throws Exception {  // UPDATED: parameter name

        log.info("Request received to patch product with ID: {}", id);

        ProductRequestDto requestDto = new ProductRequestDto();

        if (productDataJson != null && !productDataJson.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            requestDto = objectMapper.readValue(productDataJson, ProductRequestDto.class);
        }

        requestDto.setProductMainImage(productMainImage);  // UPDATED: method name
        requestDto.setProductSubImages(productSubImages);  // UPDATED: method name

        ProductResponseDto responseDto = productService.patchProduct(id, requestDto);
        log.info("Product patched successfully with ID: {}", id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Request received to delete product with ID: {}", id);
        productService.deleteProduct(id);
        log.info("Product deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        log.debug("Request received to get main image for product ID: {}", productId);
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && product.get().getProductMainImage() != null) {  // UPDATED: method name
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getProductMainImage());  // UPDATED: method name
        }
        log.warn("Main image not found for product ID: {}", productId);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}/subimage/{index}")
    public ResponseEntity<byte[]> getProductSubImage(@PathVariable Long productId, @PathVariable int index) {
        log.debug("Request received to get sub image {} for product ID: {}", index, productId);
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && index < product.get().getProductSubImages().size()) {  // UPDATED: method name
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getProductSubImages().get(index));  // UPDATED: method name
        }
        log.warn("Sub image {} not found for product ID: {}", index, productId);
        return ResponseEntity.notFound().build();
    }
}