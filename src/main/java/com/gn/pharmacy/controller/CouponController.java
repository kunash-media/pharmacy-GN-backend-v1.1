package com.gn.pharmacy.controller;

import com.gn.pharmacy.dto.request.CouponRequestDto;
import com.gn.pharmacy.dto.response.CouponResponseDto;
import com.gn.pharmacy.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create-coupon")
    public ResponseEntity<CouponResponseDto> createCoupon(@RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto responseDto = couponService.createCoupon(couponRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponseDto> getCouponById(@PathVariable Long id) {
        CouponResponseDto responseDto = couponService.getCouponById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/code/{couponCode}")
    public ResponseEntity<CouponResponseDto> getCouponByCode(@PathVariable String couponCode) {
        CouponResponseDto responseDto = couponService.getCouponByCode(couponCode);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-All-Coupons")
    public ResponseEntity<List<CouponResponseDto>> getAllCoupons() {
        List<CouponResponseDto> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CouponResponseDto>> getCouponsByStatus(@PathVariable String status) {
        List<CouponResponseDto> coupons = couponService.getCouponsByStatus(status);
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CouponResponseDto>> getCouponsByUserId(@PathVariable Long userId) {
        List<CouponResponseDto> coupons = couponService.getCouponsByUserId(userId);
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/event-type/{eventType}")
    public ResponseEntity<List<CouponResponseDto>> getCouponsByEventType(@PathVariable String eventType) {
        List<CouponResponseDto> coupons = couponService.getCouponsByEventType(eventType);
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CouponResponseDto>> getCouponsByCategory(@PathVariable String category) {
        List<CouponResponseDto> coupons = couponService.getCouponsByCategory(category);
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<CouponResponseDto>> getUnusedCoupons() {
        List<CouponResponseDto> coupons = couponService.getUnusedCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/used")
    public ResponseEntity<List<CouponResponseDto>> getUsedCoupons() {
        List<CouponResponseDto> coupons = couponService.getUsedCoupons();
        return ResponseEntity.ok(coupons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponseDto> updateCoupon(@PathVariable Long id, @RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto responseDto = couponService.updateCoupon(id, couponRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/use/{couponCode}")
    public ResponseEntity<CouponResponseDto> useCoupon(@PathVariable String couponCode) {
        CouponResponseDto responseDto = couponService.useCoupon(couponCode);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}

