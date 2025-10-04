package com.gn.pharmacy.repository;

import com.gn.pharmacy.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

    Optional<CouponEntity> findByCouponCode(String couponCode);

    List<CouponEntity> findByStatus(String status);

    List<CouponEntity> findByUserId(Long userId);

    List<CouponEntity> findByIsUsed(Boolean isUsed);

    @Query("SELECT c FROM CouponEntity c WHERE c.eventType = :eventType")
    List<CouponEntity> findByEventType(@Param("eventType") String eventType);

    @Query("SELECT c FROM CouponEntity c WHERE :category MEMBER OF c.category")
    List<CouponEntity> findByCategory(@Param("category") String category);

    @Query("SELECT c FROM CouponEntity c WHERE c.status = :status AND c.isUsed = :isUsed")
    List<CouponEntity> findByStatusAndIsUsed(@Param("status") String status, @Param("isUsed") Boolean isUsed);
}

