package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.OtpRequestDto;
import com.ph.Pharmacy.dto.response.OtpResponseDto;
import com.ph.Pharmacy.entity.AdminEntity;
import com.ph.Pharmacy.entity.OtpEntity;
import com.ph.Pharmacy.entity.UserEntity;
import com.ph.Pharmacy.repository.AdminRepository;
import com.ph.Pharmacy.repository.UserRepository;
import com.ph.Pharmacy.repository.OtpRepository;
import com.ph.Pharmacy.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {
    private static final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public OtpResponseDto generateOtp(OtpRequestDto requestDto) {
        logger.info("Generating OTP for userId: {}, purpose: {}", requestDto.getUserId(), requestDto.getPurpose());

        String otpCode = generateRandomOtp();

        // Fetch actual entities from database
        UserEntity user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDto.getUserId()));

        AdminEntity admin = null;
        if (requestDto.getAdminId() != null) {
            admin = adminRepository.findById(requestDto.getAdminId())
                    .orElseThrow(() -> new RuntimeException("Admin not found with id: " + requestDto.getAdminId()));
        }

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setUser(user);
        otpEntity.setAdmin(admin);
        otpEntity.setOtpCode(otpCode);
        otpEntity.setMobileNumber(requestDto.getMobile());
        otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        otpEntity.setEmail(requestDto.getEmail());
        otpEntity.setPurpose(requestDto.getPurpose());
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpEntity.setUsed(false);

        OtpEntity savedEntity = otpRepository.save(otpEntity);
        logger.info("OTP generated and saved with id: {}", savedEntity.getId());

        return mapToResponseDto(savedEntity);
    }

    @Override
    public OtpResponseDto verifyOtp(OtpRequestDto requestDto) {
        logger.info("Verifying OTP for code: {}, userId: {}", requestDto.getOtpCode(), requestDto.getUserId());

        Optional<OtpEntity> otpEntityOptional = otpRepository.findByOtpCodeAndUserIdAndIsUsedFalse(
                requestDto.getOtpCode(), requestDto.getUserId());

        if (otpEntityOptional.isPresent()) {
            OtpEntity otpEntity = otpEntityOptional.get();
            if (otpEntity.getExpiresAt().isAfter(LocalDateTime.now())) {
                otpEntity.setUsed(true);
                otpRepository.save(otpEntity);
                logger.info("OTP verified successfully for id: {}", otpEntity.getId());
                return mapToResponseDto(otpEntity);
            } else {
                logger.warn("OTP expired for id: {}", otpEntity.getId());
            }
        } else {
            logger.warn("Invalid OTP or already used for code: {}", requestDto.getOtpCode());
        }

        return null; // Return null so controller can handle 400 response
    }

    // Additional method to support controller's current signature
    public OtpResponseDto verifyOtp(String otpCode, Long userId) {
        OtpRequestDto requestDto = new OtpRequestDto();
        requestDto.setOtpCode(otpCode);
        requestDto.setUserId(userId);
        return verifyOtp(requestDto);
    }

    @Override
    public OtpResponseDto getOtpById(Long id) {
        Optional<OtpEntity> otpEntity = otpRepository.findById(id);
        if (otpEntity.isPresent()) {
            return mapToResponseDto(otpEntity.get());
        }
        return null;
    }

    @Override
    public List<OtpResponseDto> getAllOtps() {
        List<OtpEntity> otpEntities = otpRepository.findAll();
        return otpEntities.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public List<OtpResponseDto> getOtpsByUserId(Long userId) {
        List<OtpEntity> otpEntities = otpRepository.findByUserId(userId);
        return otpEntities.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public void deleteExpiredOtps() {
        List<OtpEntity> expiredOtps = otpRepository.findByExpiresAtBefore(LocalDateTime.now());
        otpRepository.deleteAll(expiredOtps);
        logger.info("Deleted {} expired OTPs", expiredOtps.size());
    }

    @Override
    public OtpResponseDto markOtpAsUsed(String otpCode) {
        Optional<OtpEntity> otpEntityOptional = otpRepository.findByOtpCodeAndIsUsedFalse(otpCode);
        if (otpEntityOptional.isPresent()) {
            OtpEntity otpEntity = otpEntityOptional.get();
            otpEntity.setUsed(true);
            otpRepository.save(otpEntity);
            logger.info("OTP marked as used for code: {}", otpCode);
            return mapToResponseDto(otpEntity);
        }
        return null;
    }

    private String generateRandomOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private OtpResponseDto mapToResponseDto(OtpEntity entity) {
        OtpResponseDto responseDto = new OtpResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        // CHANGE THIS LINE - Replace getAdminId() with getId()
        responseDto.setAdminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null);
        responseDto.setOtpCode(entity.getOtpCode());
        responseDto.setMobile(entity.getMobileNumber());
        responseDto.setEmail(entity.getEmail());
        responseDto.setPurpose(entity.getPurpose());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setExpiresAt(entity.getExpiresAt());
        responseDto.setUsed(entity.isUsed());
        return responseDto;
    }
}