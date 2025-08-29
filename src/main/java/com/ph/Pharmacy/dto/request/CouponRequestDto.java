package com.ph.Pharmacy.dto.request;

import java.time.LocalDate;
import java.util.List;

public class CouponRequestDto {

    private String eventType;
    private String couponDescription;
    private String couponDiscount;
    private LocalDate validFromDate;
    private LocalDate validUntilDate;
    private String status;
    private String couponCode;
    private Boolean isUsed;
    private List<String> category;
    private List<String> subCategory;
    private UserDto user;

    // Default constructor
    public CouponRequestDto() {}

    // Getters and Setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public String getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(String couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public LocalDate getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(LocalDate validFromDate) {
        this.validFromDate = validFromDate;
    }

    public LocalDate getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(LocalDate validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<String> subCategory) {
        this.subCategory = subCategory;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    // Inner UserDto class
    public static class UserDto {
        private Long userId;
        private String username;
        private String email;

        public UserDto() {}

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

