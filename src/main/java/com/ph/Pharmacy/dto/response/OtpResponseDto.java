package com.ph.Pharmacy.dto.response;

import java.time.LocalDateTime;

public class OtpResponseDto {
    private Long id;
    private Long userId;
    private Long adminId;
    private String otpCode;
    private String mobile;
    private String email;
    private String purpose;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;

    public OtpResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean isUsed) { this.isUsed = isUsed; }
}
