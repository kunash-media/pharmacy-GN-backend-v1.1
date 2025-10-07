package com.gn.pharmacy.repository;


import com.gn.pharmacy.entity.PrescriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {

    Optional<PrescriptionEntity> findByUserUserIdAndPrescriptionId(Long userId, String prescriptionId);

    Optional<PrescriptionEntity> findByPrescriptionId(String prescriptionId);

    Page<PrescriptionEntity> findByUserUserId(Long userId, Pageable pageable);

    Page<PrescriptionEntity> findByOrderStatus(String orderStatus, Pageable pageable);

    List<PrescriptionEntity> findByUserUserId(Long userId);

}
