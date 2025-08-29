package com.ph.Pharmacy.service.serviceImpl;
import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import com.ph.Pharmacy.entity.ProductEntity;
import com.ph.Pharmacy.repository.ProductRepository;
import com.ph.Pharmacy.service.ProductService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    public void ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception {
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
        return mapToResponseDto(savedEntity);
    }
    @Override
    public ProductResponseDto getProduct(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponseDto(entity);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
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