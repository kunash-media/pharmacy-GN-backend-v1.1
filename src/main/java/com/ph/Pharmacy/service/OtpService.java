package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.OtpVerificationDto;

public interface OtpService {


    void sendOtpEmail(String email);      // Add email method

    boolean verifyEmailOtp(OtpVerificationDto otpVerificationDto);    // Add email verification

}