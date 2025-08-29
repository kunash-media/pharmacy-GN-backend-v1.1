package com.ph.Pharmacy.controller;
import com.ph.Pharmacy.dto.request.WishlistRequestDto;
import com.ph.Pharmacy.dto.response.WishlistResponseDto;
import com.ph.Pharmacy.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add-to-wishlist")
    public ResponseEntity<WishlistResponseDto> addToWishlist(@RequestBody WishlistRequestDto requestDto) {
        WishlistResponseDto responseDto = wishlistService.addToWishlist(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-wishlist-by-user-Id/{userId}")
    public ResponseEntity<List<WishlistResponseDto>> getWishlistByUserId(@PathVariable Long userId) {
        List<WishlistResponseDto> wishlist = wishlistService.getWishlistByUserId(userId);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @DeleteMapping("/remove-from-wishlist/{id}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long id) {
        wishlistService.removeFromWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
