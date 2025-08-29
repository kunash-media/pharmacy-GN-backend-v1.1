package com.ph.Pharmacy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "coupons")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "coupon_description")
    private String couponDescription;

    @Column(name = "coupon_discount")
    private String couponDiscount;

    @Column(name = "valid_from_date")
    private LocalDate validFromDate;

    @Column(name = "valid_until_date")
    private LocalDate validUntilDate;

    @Column(name = "status")
    private String status;

    @Column(name = "coupon_code", unique = true)
    private String couponCode;

    @Column(name = "is_used")
    private Boolean isUsed;

    @ElementCollection
    @CollectionTable(name = "coupon_categories", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "category")
    private List<String> category;

    @ElementCollection
    @CollectionTable(name = "coupon_sub_categories", joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "sub_category")
    private List<String> subCategory;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    // Default constructor
    public CouponEntity() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

