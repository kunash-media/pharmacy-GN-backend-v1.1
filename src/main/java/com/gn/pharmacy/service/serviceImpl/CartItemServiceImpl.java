package com.gn.pharmacy.service.serviceImpl;

import com.gn.pharmacy.dto.request.CartItemRequestDto;
import com.gn.pharmacy.dto.response.CartItemResponseDto;
import com.gn.pharmacy.entity.CartItemEntity;
import com.gn.pharmacy.repository.CartItemRepository;
import com.gn.pharmacy.service.CartItemService;
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