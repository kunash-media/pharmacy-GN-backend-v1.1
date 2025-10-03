package com.ph.Pharmacy.service.serviceImpl;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception {
        log.debug("Creating new product with name: {}", requestDto.getName());  // ADDED: Logging

        ProductEntity entity = new ProductEntity();
        entity.setName(requestDto.getName());
        entity.setCategory(requestDto.getCategory());
        entity.setSubcategory(requestDto.getSubcategory());
        entity.setPrice(requestDto.getPrice());
        entity.setStock(requestDto.getStock());
        entity.setStatus(requestDto.getStatus());
        entity.setDescription(requestDto.getDescription());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setMainImage(requestDto.getMainImage().getBytes());
        List<byte[]> subImages = requestDto.getSubImages() != null
                ? requestDto.getSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setSubImages(subImages);
        entity.setDynamicFields(requestDto.getDynamicFields());

        ProductEntity savedEntity = productRepository.save(entity);
        log.debug("Product saved with ID: {}", savedEntity.getId());  // ADDED: Logging
        return mapToResponseDto(savedEntity);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        log.debug("Fetching product by ID: {}", id);  // ADDED: Logging
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);  // ADDED: Logging
                    return new RuntimeException("Product not found");
                });
        return mapToResponseDto(entity);
    }

    // ========== UPDATED: Added pagination support ==========
    @Override
    public Page<ProductResponseDto> getAllProducts(int page, int size) {
        log.debug("Fetching all products - page: {}, size: {}", page, size);  // ADDED: Logging
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        log.debug("Found {} products on page {}", productPage.getNumberOfElements(), page);  // ADDED: Logging
        return productPage.map(this::mapToResponseDto);
    }
    // ========== END UPDATED ==========

    // ========== ADDED: New method for get by category ==========
    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        log.debug("Fetching products by category: {}", category);  // ADDED: Logging
        List<ProductEntity> products = productRepository.findByCategory(category);
        log.debug("Found {} products for category: {}", products.size(), category);  // ADDED: Logging
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
    // ========== END ADDED ==========

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Updating product with ID: {}", id);  // ADDED: Logging

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);  // ADDED: Logging
                    return new RuntimeException("Product not found");
                });
        entity.setName(requestDto.getName());
        entity.setCategory(requestDto.getCategory());
        entity.setSubcategory(requestDto.getSubcategory());
        entity.setPrice(requestDto.getPrice());
        entity.setStock(requestDto.getStock());
        entity.setStatus(requestDto.getStatus());
        entity.setDescription(requestDto.getDescription());
        entity.setMainImage(requestDto.getMainImage().getBytes());
        List<byte[]> subImages = requestDto.getSubImages() != null
                ? requestDto.getSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setSubImages(subImages);
        entity.setDynamicFields(requestDto.getDynamicFields());

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product updated successfully with ID: {}", id);  // ADDED: Logging
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Patching product with ID: {}", id);  // ADDED: Logging

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);  // ADDED: Logging
                    return new RuntimeException("Product not found");
                });
        if (requestDto.getName() != null) entity.setName(requestDto.getName());
        if (requestDto.getCategory() != null) entity.setCategory(requestDto.getCategory());
        if (requestDto.getSubcategory() != null) entity.setSubcategory(requestDto.getSubcategory());
        if (requestDto.getPrice() != null) entity.setPrice(requestDto.getPrice());
        if (requestDto.getStock() != null) entity.setStock(requestDto.getStock());
        if (requestDto.getStatus() != null) entity.setStatus(requestDto.getStatus());
        if (requestDto.getDescription() != null) entity.setDescription(requestDto.getDescription());
        if (requestDto.getMainImage() != null) entity.setMainImage(requestDto.getMainImage().getBytes());
        if (requestDto.getSubImages() != null) {
            List<byte[]> subImages = requestDto.getSubImages().stream().map(file -> {
                try {
                    return file.getBytes();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            entity.setSubImages(subImages);
        }
        if (requestDto.getDynamicFields() != null) entity.setDynamicFields(requestDto.getDynamicFields());

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product patched successfully with ID: {}", id);  // ADDED: Logging
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);  // ADDED: Logging
        productRepository.deleteById(id);
        log.debug("Product deleted successfully with ID: {}", id);  // ADDED: Logging
    }

    private ProductResponseDto mapToResponseDto(ProductEntity entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        responseDto.setCategory(entity.getCategory());
        responseDto.setSubcategory(entity.getSubcategory());
        responseDto.setPrice(entity.getPrice());
        responseDto.setStock(entity.getStock());
        responseDto.setStatus(entity.getStatus());
        responseDto.setDescription(entity.getDescription());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setMainImage("/api/products/" + entity.getId() + "/image");
        responseDto.setSubImages(IntStream.range(0, entity.getSubImages().size())
                .mapToObj(i -> "/api/products/" + entity.getId() + "/subimage/" + i)
                .collect(Collectors.toList()));
        responseDto.setDynamicFields(entity.getDynamicFields());
        return responseDto;
    }
}