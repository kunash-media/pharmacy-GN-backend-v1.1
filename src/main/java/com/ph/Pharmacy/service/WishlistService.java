package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.WishlistRequestDto;
import com.ph.Pharmacy.dto.response.WishlistResponseDto;

import java.util.List;

public interface WishlistService {
    WishlistResponseDto addToWishlist(WishlistRequestDto requestDto);
    List<WishlistResponseDto> getWishlistByUserId(Long userId);
    void removeFromWishlist(Long id);
}
