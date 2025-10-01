package com.ph.Pharmacy.repository;


import com.ph.Pharmacy.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, String> {
    List<PrescriptionEntity> findByStatus(String status);
    List<PrescriptionEntity> findByPatientNameContainingIgnoreCase(String searchTerm);
}
