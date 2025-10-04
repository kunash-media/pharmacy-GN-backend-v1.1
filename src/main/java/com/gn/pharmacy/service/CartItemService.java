package com.gn.pharmacy.service;

import com.gn.pharmacy.dto.request.CartItemRequestDto;
import com.gn.pharmacy.dto.response.CartItemResponseDto;

import java.util.List;

public interface CartItemService {
    CartItemResponseDto addToCart(CartItemRequestDto requestDto);
    List<CartItemResponseDto> getCartItemsByUserId(Long userId);
    CartItemResponseDto updateCartItem(Long id, CartItemRequestDto requestDto);
    void removeFromCart(Long id);
}
