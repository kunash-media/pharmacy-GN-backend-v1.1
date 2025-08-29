package com.ph.Pharmacy.dto.request;

public class WishlistRequestDto {
    private Long userId;
    private Long productId;

    // Constructors
    public WishlistRequestDto() {}

    public WishlistRequestDto(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
