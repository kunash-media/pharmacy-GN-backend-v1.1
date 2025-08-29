package com.ph.Pharmacy.repository;

import com.ph.Pharmacy.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByOtpCodeAndUserIdAndIsUsedFalse(String otpCode, Long userId);
    Optional<OtpEntity> findByOtpCodeAndIsUsedFalse(String otpCode);
    List<OtpEntity> findByUserId(Long userId);
    List<OtpEntity> findByExpiresAtBefore(LocalDateTime dateTime);

    // Alternative if your relationship is different:
    Optional<OtpEntity> findByOtpCodeAndUser_UserIdAndIsUsedFalse(String otpCode, Long userId);
    List<OtpEntity> findByUser_UserId(Long userId);
}

