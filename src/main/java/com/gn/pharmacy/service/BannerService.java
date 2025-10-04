package com.gn.pharmacy.service;

import com.gn.pharmacy.dto.request.BannerRequestDto;
import com.gn.pharmacy.dto.response.BannerResponseDto;

import java.util.List;

public interface BannerService {
    BannerResponseDto createBanner(BannerRequestDto requestDto) throws Exception;
    BannerResponseDto getBannerById(Long id) throws Exception;
    List<BannerResponseDto> getAllBanners();
    BannerResponseDto updateBanner(Long id, BannerRequestDto requestDto) throws Exception;
    void deleteBanner(Long id) throws Exception;
}

