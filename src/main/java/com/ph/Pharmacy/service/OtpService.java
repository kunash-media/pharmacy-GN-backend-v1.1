package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.OtpRequestDto;
import com.ph.Pharmacy.dto.response.OtpResponseDto;
import java.util.List;

public interface OtpService {
    OtpResponseDto generateOtp(OtpRequestDto requestDto);
    OtpResponseDto verifyOtp(OtpRequestDto requestDto);
    OtpResponseDto getOtpById(Long id);
    List<OtpResponseDto> getAllOtps();
    List<OtpResponseDto> getOtpsByUserId(Long userId);
    void deleteExpiredOtps();
    OtpResponseDto markOtpAsUsed(String otpCode);
}