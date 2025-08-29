package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.ProductRequestDto;
import com.ph.Pharmacy.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception;

    ProductResponseDto getProduct(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception;

    ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception;

    void deleteProduct(Long id);
}
