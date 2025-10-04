package com.gn.pharmacy.service;


import com.gn.pharmacy.dto.request.OtpVerificationDto;

public interface OtpService {


    void sendOtpEmail(String email);      // Add email method

    boolean verifyEmailOtp(OtpVerificationDto otpVerificationDto);    // Add email verification

}