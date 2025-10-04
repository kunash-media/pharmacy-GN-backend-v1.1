package com.gn.pharmacy.service;


import com.gn.pharmacy.dto.request.WishlistRequestDto;
import com.gn.pharmacy.dto.response.WishlistResponseDto;

import java.util.List;

public interface WishlistService {
    WishlistResponseDto addToWishlist(WishlistRequestDto requestDto);
    List<WishlistResponseDto> getWishlistByUserId(Long userId);
    void removeFromWishlist(Long id);
}
