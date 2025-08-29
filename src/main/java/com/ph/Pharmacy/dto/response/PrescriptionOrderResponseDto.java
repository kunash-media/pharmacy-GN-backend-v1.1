package com.ph.Pharmacy.dto.response;

import com.ph.Pharmacy.entity.PrescriptionOrderEntity;

import java.time.LocalDateTime;

public class PrescriptionOrderResponseDto {

    private Long id;
    private Long userId;
    private String prescriptionImageUrl;
    private String shippingAddress;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingState;
    private String shippingPincode;
    private String shippingCountry;
    private String shippingFirstName;
    private String shippingLastName;
    private String shippingEmail;
    private String shippingPhone;
    private Boolean shippingIsBilling;
    private String billingCustomerName;
    private String billingLastName;
    private String billingAddress;
    private String billingAddress2;
    private String billingCity;
    private String billingPincode;
    private String billingState;
    private String billingCountry;
    private String billingEmail;
    private String billingPhone;
    private String orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public PrescriptionOrderResponseDto() {}

    public PrescriptionOrderResponseDto(PrescriptionOrderEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.prescriptionImageUrl = entity.getPrescriptionImageUrl();
        this.shippingAddress = entity.getShippingAddress();
        this.shippingAddress2 = entity.getShippingAddress2();
        this.shippingCity = entity.getShippingCity();
        this.shippingState = entity.getShippingState();
        this.shippingPincode = entity.getShippingPincode();
        this.shippingCountry = entity.getShippingCountry();
        this.shippingFirstName = entity.getShippingFirstName();
        this.shippingLastName = entity.getShippingLastName();
        this.shippingEmail = entity.getShippingEmail();
        this.shippingPhone = entity.getShippingPhone();
        this.shippingIsBilling = entity.getShippingIsBilling();
        this.billingCustomerName = entity.getBillingCustomerName();
        this.billingLastName = entity.getBillingLastName();
        this.billingAddress = entity.getBillingAddress();
        this.billingAddress2 = entity.getBillingAddress2();
        this.billingCity = entity.getBillingCity();
        this.billingPincode = entity.getBillingPincode();
        this.billingState = entity.getBillingState();
        this.billingCountry = entity.getBillingCountry();
        this.billingEmail = entity.getBillingEmail();
        this.billingPhone = entity.getBillingPhone();
        this.orderStatus = entity.getOrderStatus() != null ? entity.getOrderStatus().name() : null;
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
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

    public String getPrescriptionImageUrl() {
        return prescriptionImageUrl;
    }

    public void setPrescriptionImageUrl(String prescriptionImageUrl) {
        this.prescriptionImageUrl = prescriptionImageUrl;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingPincode() {
        return shippingPincode;
    }

    public void setShippingPincode(String shippingPincode) {
        this.shippingPincode = shippingPincode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public String getShippingEmail() {
        return shippingEmail;
    }

    public void setShippingEmail(String shippingEmail) {
        this.shippingEmail = shippingEmail;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public Boolean getShippingIsBilling() {
        return shippingIsBilling;
    }

    public void setShippingIsBilling(Boolean shippingIsBilling) {
        this.shippingIsBilling = shippingIsBilling;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPincode() {
        return billingPincode;
    }

    public void setBillingPincode(String billingPincode) {
        this.billingPincode = billingPincode;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
