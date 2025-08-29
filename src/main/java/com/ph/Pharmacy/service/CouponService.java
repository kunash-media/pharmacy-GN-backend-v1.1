package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.CouponRequestDto;
import com.ph.Pharmacy.dto.response.CouponResponseDto;

import java.util.List;

public interface CouponService {

    CouponResponseDto createCoupon(CouponRequestDto couponRequestDto);

    CouponResponseDto getCouponById(Long id);

    CouponResponseDto getCouponByCode(String couponCode);

    List<CouponResponseDto> getAllCoupons();

    List<CouponResponseDto> getCouponsByStatus(String status);

    List<CouponResponseDto> getCouponsByUserId(Long userId);

    List<CouponResponseDto> getCouponsByEventType(String eventType);

    List<CouponResponseDto> getCouponsByCategory(String category);

    List<CouponResponseDto> getUnusedCoupons();

    List<CouponResponseDto> getUsedCoupons();

    CouponResponseDto updateCoupon(Long id, CouponRequestDto couponRequestDto);

    CouponResponseDto useCoupon(String couponCode);

    void deleteCoupon(Long id);
}

