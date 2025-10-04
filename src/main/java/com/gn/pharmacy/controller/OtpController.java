package com.gn.pharmacy.controller;

import com.gn.pharmacy.dto.request.EmailRequest;
import com.gn.pharmacy.dto.request.OtpVerificationDto;
import com.gn.pharmacy.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-email-body")
    public ResponseEntity<String> sendEmailOtp(@RequestBody EmailRequest emailRequest) {
        try {
            otpService.sendOtpEmail(emailRequest.getEmail());
            return ResponseEntity.ok("OTP sent successfully to email: " + emailRequest.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmailOtp(@RequestBody OtpVerificationDto otpVerificationDto) {
        try {
            boolean isValid = otpService.verifyEmailOtp(otpVerificationDto);
            if (isValid) {
                return ResponseEntity.ok("OTP verified successfully");
            } else {
                return ResponseEntity.badRequest().body("Invalid or expired OTP");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Verification failed: " + e.getMessage());
        }
    }


}