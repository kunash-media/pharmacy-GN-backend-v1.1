package com.ph.Pharmacy.service.serviceImpl;


import com.ph.Pharmacy.dto.request.CouponRequestDto;
import com.ph.Pharmacy.dto.response.CouponResponseDto;
import com.ph.Pharmacy.entity.CouponEntity;
import com.ph.Pharmacy.repository.CouponRepository;
import com.ph.Pharmacy.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public CouponResponseDto createCoupon(CouponRequestDto couponRequestDto) {
        CouponEntity couponEntity = convertToEntity(couponRequestDto);
        CouponEntity savedCoupon = couponRepository.save(couponEntity);
        return convertToResponseDto(savedCoupon);
    }

    @Override
    public CouponResponseDto getCouponById(Long id) {
        CouponEntity couponEntity = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        return convertToResponseDto(couponEntity);
    }

    @Override
    public CouponResponseDto getCouponByCode(String couponCode) {
        CouponEntity couponEntity = couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new RuntimeException("Coupon not found with code: " + couponCode));
        return convertToResponseDto(couponEntity);
    }

    @Override
    public List<CouponResponseDto> getAllCoupons() {
        List<CouponEntity> coupons = couponRepository.findAll();
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getCouponsByStatus(String status) {
        List<CouponEntity> coupons = couponRepository.findByStatus(status);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getCouponsByUserId(Long userId) {
        List<CouponEntity> coupons = couponRepository.findByUserId(userId);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getCouponsByEventType(String eventType) {
        List<CouponEntity> coupons = couponRepository.findByEventType(eventType);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getCouponsByCategory(String category) {
        List<CouponEntity> coupons = couponRepository.findByCategory(category);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getUnusedCoupons() {
        List<CouponEntity> coupons = couponRepository.findByIsUsed(false);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponResponseDto> getUsedCoupons() {
        List<CouponEntity> coupons = couponRepository.findByIsUsed(true);
        return coupons.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponseDto updateCoupon(Long id, CouponRequestDto couponRequestDto) {
        CouponEntity existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));

        // Update fields
        existingCoupon.setEventType(couponRequestDto.getEventType());
        existingCoupon.setCouponDescription(couponRequestDto.getCouponDescription());
        existingCoupon.setCouponDiscount(couponRequestDto.getCouponDiscount());
        existingCoupon.setValidFromDate(couponRequestDto.getValidFromDate());
        existingCoupon.setValidUntilDate(couponRequestDto.getValidUntilDate());
        existingCoupon.setStatus(couponRequestDto.getStatus());
        existingCoupon.setCouponCode(couponRequestDto.getCouponCode());
        existingCoupon.setIsUsed(couponRequestDto.getIsUsed());
        existingCoupon.setCategory(couponRequestDto.getCategory());
        existingCoupon.setSubCategory(couponRequestDto.getSubCategory());

        if (couponRequestDto.getUser() != null) {
            existingCoupon.setUserId(couponRequestDto.getUser().getUserId());
            existingCoupon.setUsername(couponRequestDto.getUser().getUsername());
            existingCoupon.setEmail(couponRequestDto.getUser().getEmail());
        }

        CouponEntity updatedCoupon = couponRepository.save(existingCoupon);
        return convertToResponseDto(updatedCoupon);
    }

    @Override
    public CouponResponseDto useCoupon(String couponCode) {
        CouponEntity couponEntity = couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new RuntimeException("Coupon not found with code: " + couponCode));

        couponEntity.setIsUsed(true);
        CouponEntity savedCoupon = couponRepository.save(couponEntity);
        return convertToResponseDto(savedCoupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new RuntimeException("Coupon not found with id: " + id);
        }
        couponRepository.deleteById(id);
    }

    // Helper method to convert RequestDto to Entity
    private CouponEntity convertToEntity(CouponRequestDto requestDto) {
        CouponEntity entity = new CouponEntity();
        entity.setEventType(requestDto.getEventType());
        entity.setCouponDescription(requestDto.getCouponDescription());
        entity.setCouponDiscount(requestDto.getCouponDiscount());
        entity.setValidFromDate(requestDto.getValidFromDate());
        entity.setValidUntilDate(requestDto.getValidUntilDate());
        entity.setStatus(requestDto.getStatus());
        entity.setCouponCode(requestDto.getCouponCode());
        entity.setIsUsed(requestDto.getIsUsed());
        entity.setCategory(requestDto.getCategory());
        entity.setSubCategory(requestDto.getSubCategory());

        if (requestDto.getUser() != null) {
            entity.setUserId(requestDto.getUser().getUserId());
            entity.setUsername(requestDto.getUser().getUsername());
            entity.setEmail(requestDto.getUser().getEmail());
        }

        return entity;
    }

    // Helper method to convert Entity to ResponseDto
    private CouponResponseDto convertToResponseDto(CouponEntity entity) {
        CouponResponseDto responseDto = new CouponResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setEventType(entity.getEventType());
        responseDto.setCouponDescription(entity.getCouponDescription());
        responseDto.setCouponDiscount(entity.getCouponDiscount());
        responseDto.setValidFromDate(entity.getValidFromDate());
        responseDto.setValidUntilDate(entity.getValidUntilDate());
        responseDto.setStatus(entity.getStatus());
        responseDto.setCouponCode(entity.getCouponCode());
        responseDto.setIsUsed(entity.getIsUsed());
        responseDto.setCategory(entity.getCategory());
        responseDto.setSubCategory(entity.getSubCategory());

        // Create user DTO
        CouponResponseDto.UserDto userDto = new CouponResponseDto.UserDto();
        userDto.setUserId(entity.getUserId());
        userDto.setUsername(entity.getUsername());
        userDto.setEmail(entity.getEmail());
        responseDto.setUser(userDto);

        return responseDto;
    }
}

