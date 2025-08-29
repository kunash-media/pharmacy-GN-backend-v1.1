package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.CartItemRequestDto;
import com.ph.Pharmacy.dto.response.CartItemResponseDto;

import java.util.List;

public interface CartItemService {
    CartItemResponseDto addToCart(CartItemRequestDto requestDto);
    List<CartItemResponseDto> getCartItemsByUserId(Long userId);
    CartItemResponseDto updateCartItem(Long id, CartItemRequestDto requestDto);
    void removeFromCart(Long id);
}
