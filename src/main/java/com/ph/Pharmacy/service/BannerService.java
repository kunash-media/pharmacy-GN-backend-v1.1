package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.BannerRequestDto;
import com.ph.Pharmacy.dto.response.BannerResponseDto;

import java.util.List;

public interface BannerService {
    BannerResponseDto createBanner(BannerRequestDto requestDto) throws Exception;
    BannerResponseDto getBannerById(Long id) throws Exception;
    List<BannerResponseDto> getAllBanners();
    BannerResponseDto updateBanner(Long id, BannerRequestDto requestDto) throws Exception;
    void deleteBanner(Long id) throws Exception;
}

