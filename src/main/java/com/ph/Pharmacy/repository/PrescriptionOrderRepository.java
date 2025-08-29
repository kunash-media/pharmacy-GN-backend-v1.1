package com.ph.Pharmacy.repository;
import com.ph.Pharmacy.entity.PrescriptionOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionOrderRepository extends JpaRepository<PrescriptionOrderEntity, Long> {

    List<PrescriptionOrderEntity> findByUserId(Long userId);

    List<PrescriptionOrderEntity> findByOrderStatus(PrescriptionOrderEntity.OrderStatus orderStatus);

    @Query("SELECT p FROM PrescriptionOrderEntity p WHERE p.userId = :userId AND p.orderStatus = :status")
    List<PrescriptionOrderEntity> findByUserIdAndStatus(@Param("userId") Long userId,
                                                        @Param("status") PrescriptionOrderEntity.OrderStatus status);

    Optional<PrescriptionOrderEntity> findByIdAndUserId(Long id, Long userId);

    @Query("SELECT p FROM PrescriptionOrderEntity p WHERE p.shippingEmail = :email")
    List<PrescriptionOrderEntity> findByShippingEmail(@Param("email") String email);

    @Query("SELECT p FROM PrescriptionOrderEntity p WHERE p.shippingPhone = :phone")
    List<PrescriptionOrderEntity> findByShippingPhone(@Param("phone") String phone);

    @Query("SELECT COUNT(p) FROM PrescriptionOrderEntity p WHERE p.userId = :userId")
    Long countOrdersByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM PrescriptionOrderEntity p WHERE p.shippingPincode = :pincode")
    List<PrescriptionOrderEntity> findByShippingPincode(@Param("pincode") String pincode);

    boolean existsByUserIdAndPrescriptionImageUrl(Long userId, String prescriptionImageUrl);
}
