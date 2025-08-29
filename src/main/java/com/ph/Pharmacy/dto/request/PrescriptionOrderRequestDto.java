package com.ph.Pharmacy.dto.request;

public class PrescriptionOrderRequestDto {

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

    // Constructors
    public PrescriptionOrderRequestDto() {}

    public PrescriptionOrderRequestDto(Long userId, String prescriptionImageUrl,
                                       String shippingAddress, String shippingCity,
                                       String shippingState, String shippingPincode,
                                       String shippingCountry, String shippingFirstName,
                                       String shippingLastName, String shippingEmail,
                                       String shippingPhone) {
        this.userId = userId;
        this.prescriptionImageUrl = prescriptionImageUrl;
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingPincode = shippingPincode;
        this.shippingCountry = shippingCountry;
        this.shippingFirstName = shippingFirstName;
        this.shippingLastName = shippingLastName;
        this.shippingEmail = shippingEmail;
        this.shippingPhone = shippingPhone;
    }

    // Getters and Setters
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
}
