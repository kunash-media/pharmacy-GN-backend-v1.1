package com.ph.Pharmacy.service.serviceImpl;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception {
        log.debug("Creating new product with name: {}", requestDto.getProductName());

        ProductEntity entity = new ProductEntity();

        entity.setProductName(requestDto.getProductName());
        entity.setProductCategory(requestDto.getProductCategory());
        entity.setProductSubCategory(requestDto.getProductSubCategory());
        entity.setProductPrice(requestDto.getProductPrice());
        entity.setProductOldPrice(requestDto.getProductOldPrice());
        entity.setProductStock(requestDto.getProductStock());
        entity.setProductStatus(requestDto.getProductStatus());
        entity.setProductDescription(requestDto.getProductDescription());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setProductQuantity(requestDto.getProductQuantity());
        entity.setProductMainImage(requestDto.getProductMainImage().getBytes());

        List<byte[]> subImages = requestDto.getProductSubImages() != null
                ? requestDto.getProductSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setProductSubImages(subImages);
        entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity savedEntity = productRepository.save(entity);
        log.debug("Product saved with ID: {}", savedEntity.getProductId());
        return mapToResponseDto(savedEntity);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        log.debug("Fetching product by ID: {}", id);
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });
        return mapToResponseDto(entity);
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page, int size) {
        log.debug("Fetching all products - page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        log.debug("Found {} products on page {}", productPage.getNumberOfElements(), page);
        return productPage.map(this::mapToResponseDto);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        log.debug("Fetching products by category: {}", category);
        List<ProductEntity> products = productRepository.findByProductCategory(category);
        log.debug("Found {} products for category: {}", products.size(), category);
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsBySubCategory(String subCategory) {
        log.debug("Fetching products by sub category: {}", subCategory);
        List<ProductEntity> products = productRepository.findByProductSubCategory(subCategory);
        log.debug("Found {} products for sub category: {}", products.size(), subCategory);
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Updating product with ID: {}", id);

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });
        entity.setProductName(requestDto.getProductName());
        entity.setProductCategory(requestDto.getProductCategory());
        entity.setProductSubCategory(requestDto.getProductSubCategory());
        entity.setProductPrice(requestDto.getProductPrice());
        entity.setProductOldPrice(requestDto.getProductOldPrice());
        entity.setProductStock(requestDto.getProductStock());
        entity.setProductStatus(requestDto.getProductStatus());
        entity.setProductDescription(requestDto.getProductDescription());
        entity.setProductQuantity(requestDto.getProductQuantity());
        entity.setProductMainImage(requestDto.getProductMainImage().getBytes());

        List<byte[]> subImages = requestDto.getProductSubImages() != null
                ? requestDto.getProductSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setProductSubImages(subImages);
        entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product updated successfully with ID: {}", id);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Patching product with ID: {}", id);

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });

        if (requestDto.getProductName() != null) entity.setProductName(requestDto.getProductName());
        if (requestDto.getProductCategory() != null) entity.setProductCategory(requestDto.getProductCategory());
        if (requestDto.getProductSubCategory() != null) entity.setProductSubCategory(requestDto.getProductSubCategory());
        if (requestDto.getProductPrice() != null) entity.setProductPrice(requestDto.getProductPrice());
        if (requestDto.getProductOldPrice() != null) entity.setProductOldPrice(requestDto.getProductOldPrice());
        if (requestDto.getProductStock() != null) entity.setProductStock(requestDto.getProductStock());
        if (requestDto.getProductStatus() != null) entity.setProductStatus(requestDto.getProductStatus());
        if (requestDto.getProductQuantity() != null) entity.setProductQuantity(requestDto.getProductQuantity());
        if (requestDto.getProductDescription() != null) entity.setProductDescription(requestDto.getProductDescription());
        if (requestDto.getProductMainImage() != null) entity.setProductMainImage(requestDto.getProductMainImage().getBytes());
        if (requestDto.getProductSubImages() != null) {
            List<byte[]> subImages = requestDto.getProductSubImages().stream().map(file -> {
                try {
                    return file.getBytes();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            entity.setProductSubImages(subImages);
        }
        if (requestDto.getProductDynamicFields() != null) entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes if provided
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product patched successfully with ID: {}", id);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.debug("Product deleted successfully with ID: {}", id);
    }

    private ProductResponseDto mapToResponseDto(ProductEntity entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(entity.getProductId());
        responseDto.setProductName(entity.getProductName());
        responseDto.setProductCategory(entity.getProductCategory());
        responseDto.setProductSubCategory(entity.getProductSubCategory());
        responseDto.setProductPrice(entity.getProductPrice());
        responseDto.setProductOldPrice(entity.getProductOldPrice());
        responseDto.setProductStock(entity.getProductStock());
        responseDto.setProductStatus(entity.getProductStatus());
        responseDto.setProductDescription(entity.getProductDescription());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setProductQuantity(entity.getProductQuantity());
        responseDto.setProductMainImage("/api/products/" + entity.getProductId() + "/image");
        responseDto.setProductSubImages(IntStream.range(0, entity.getProductSubImages().size())
                .mapToObj(i -> "/api/products/" + entity.getProductId() + "/subimage/" + i)
                .collect(Collectors.toList()));
        responseDto.setProductDynamicFields(entity.getProductDynamicFields());

        // ADDED: Set product sizes
        responseDto.setProductSizes(entity.getProductSizes());

        return responseDto;
    }
}