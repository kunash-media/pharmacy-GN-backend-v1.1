package com.ph.Pharmacy.dto.response;


public class WishlistResponseDto {
    private Long id;
    private Long userId;
    private Long productId;

    // Constructors
    public WishlistResponseDto() {}

    public WishlistResponseDto(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
