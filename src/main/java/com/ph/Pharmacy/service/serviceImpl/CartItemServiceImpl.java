package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.CartItemRequestDto;
import com.ph.Pharmacy.dto.response.CartItemResponseDto;
import com.ph.Pharmacy.entity.CartItemEntity;
import com.ph.Pharmacy.repository.CartItemRepository;
import com.ph.Pharmacy.service.CartItemService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    // Initialize dummy data
    @PostConstruct
    public void init() {
        List<CartItemEntity> dummyData = new ArrayList<>();
        dummyData.add(new CartItemEntity(1L, 101L, 2));
        dummyData.add(new CartItemEntity(1L, 102L, 1));
        dummyData.add(new CartItemEntity(2L, 103L, 3));
        cartItemRepository.saveAll(dummyData);
    }

    @Override
    public CartItemResponseDto addToCart(CartItemRequestDto requestDto) {
        CartItemEntity entity = new CartItemEntity(requestDto.getUserId(), requestDto.getProductId(), requestDto.getQuantity());
        CartItemEntity savedEntity = cartItemRepository.save(entity);
        return new CartItemResponseDto(savedEntity.getId(), savedEntity.getUserId(), savedEntity.getProductId(), savedEntity.getQuantity());
    }

    @Override
    public List<CartItemResponseDto> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(entity -> new CartItemResponseDto(entity.getId(), entity.getUserId(), entity.getProductId(), entity.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponseDto updateCartItem(Long id, CartItemRequestDto requestDto) {
        CartItemEntity entity = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart item not found"));
        entity.setQuantity(requestDto.getQuantity());
        CartItemEntity updatedEntity = cartItemRepository.save(entity);
        return new CartItemResponseDto(updatedEntity.getId(), updatedEntity.getUserId(), updatedEntity.getProductId(), updatedEntity.getQuantity());
    }

    @Override
    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }
}