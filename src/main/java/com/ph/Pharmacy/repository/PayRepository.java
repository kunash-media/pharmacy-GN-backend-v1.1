package com.ph.Pharmacy.repository;


import com.ph.Pharmacy.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRepository extends JpaRepository<PayEntity, Long> {

    Optional<PayEntity> findByRazorpayOrderId(String razorpayOrderId);
    Optional<PayEntity> findByRazorpayPaymentId(String razorpayPaymentId);
    Optional<PayEntity> findByReceipt(String receipt);
}

