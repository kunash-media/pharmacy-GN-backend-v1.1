package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.CartItemRequestDto;
import com.ph.Pharmacy.dto.response.CartItemResponseDto;
import com.ph.Pharmacy.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<CartItemResponseDto> addToCart(@RequestBody CartItemRequestDto requestDto) {
        CartItemResponseDto responseDto = cartItemService.addToCart(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-cart-by-user-Id/{userId}")
    public ResponseEntity<List<CartItemResponseDto>> getCartItemsByUserId(@PathVariable Long userId) {
        List<CartItemResponseDto> cartItems = cartItemService.getCartItemsByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PutMapping("/update-cart-item/{id}")
    public ResponseEntity<CartItemResponseDto> updateCartItem(@PathVariable Long id, @RequestBody CartItemRequestDto requestDto) {
        CartItemResponseDto responseDto = cartItemService.updateCartItem(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/remove-from-cart/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartItemService.removeFromCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}