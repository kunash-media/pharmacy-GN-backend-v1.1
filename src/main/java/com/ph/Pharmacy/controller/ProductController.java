package com.ph.Pharmacy.controller;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/create-Product")
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory,
            @RequestParam("price") Double price,
            @RequestParam("stock") Integer stock,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages,
            @RequestParam Map<String, String> dynamicFields) throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(name);
        requestDto.setCategory(category);
        requestDto.setSubcategory(subcategory);
        requestDto.setPrice(price);
        requestDto.setStock(stock);
        requestDto.setStatus(status);
        requestDto.setDescription(description);
        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);
        requestDto.setDynamicFields(dynamicFields);

        ProductResponseDto responseDto = productService.createProduct(requestDto);
        return ResponseEntity.ok(responseDto);
    }


    @GetMapping("/get-Product/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto responseDto = productService.getProduct(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-All-Products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> responseDtos = productService.getAllProducts();
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/update-Product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory,
            @RequestParam("price") Double price,
            @RequestParam("stock") Integer stock,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages,
            @RequestParam Map<String, String> dynamicFields) throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(name);
        requestDto.setCategory(category);
        requestDto.setSubcategory(subcategory);
        requestDto.setPrice(price);
        requestDto.setStock(stock);
        requestDto.setStatus(status);
        requestDto.setDescription(description);
        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);
        requestDto.setDynamicFields(dynamicFields);

        ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/patch-Product/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subcategory", required = false) String subcategory,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestParam(value = "subImages", required = false) List<MultipartFile> subImages,
            @RequestParam Map<String, String> dynamicFields) throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(name);
        requestDto.setCategory(category);
        requestDto.setSubcategory(subcategory);
        requestDto.setPrice(price);
        requestDto.setStock(stock);
        requestDto.setStatus(status);
        requestDto.setDescription(description);
        requestDto.setMainImage(mainImage);
        requestDto.setSubImages(subImages);
        requestDto.setDynamicFields(dynamicFields);

        ProductResponseDto responseDto = productService.patchProduct(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete-Product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && product.get().getMainImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getMainImage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}/subimage/{index}")
    public ResponseEntity<byte[]> getProductSubImage(@PathVariable Long productId, @PathVariable int index) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent() && index < product.get().getSubImages().size()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(product.get().getSubImages().get(index));
        }
        return ResponseEntity.notFound().build();
    }
}