package com.ph.Pharmacy.controller;
import com.ph.Pharmacy.dto.request.OtpRequestDto;
import com.ph.Pharmacy.dto.response.OtpResponseDto;
import com.ph.Pharmacy.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private static final Logger logger = LoggerFactory.getLogger(OtpController.class);

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public OtpResponseDto generateOtp(@RequestBody OtpRequestDto requestDto) {
        logger.info("Received request to generate OTP for userId: {}", requestDto.getUserId());
        return otpService.generateOtp(requestDto);
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponseDto> verifyOtp(@RequestParam String otpCode, @RequestParam Long userId) {
        // Create request DTO
        OtpRequestDto requestDto = new OtpRequestDto();
        requestDto.setOtpCode(otpCode);
        requestDto.setUserId(userId);

        OtpResponseDto response = otpService.verifyOtp(requestDto);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}

