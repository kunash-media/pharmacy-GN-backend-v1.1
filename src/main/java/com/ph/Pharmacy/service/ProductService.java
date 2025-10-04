package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception;

    ProductResponseDto getProduct(Long id);

    // ========== UPDATED: Changed return type to Page ==========
    Page<ProductResponseDto> getAllProducts(int page, int size);
    // ========== END UPDATED ========== //

    // ========== ADDED: New method for get by category ========== //
    List<ProductResponseDto> getProductsByCategory(String category);
    // ========== END ADDED ========== //

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception;

    ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception;

    void deleteProduct(Long id);

    List<ProductResponseDto> getProductsBySubCategory(String subCategory);
}