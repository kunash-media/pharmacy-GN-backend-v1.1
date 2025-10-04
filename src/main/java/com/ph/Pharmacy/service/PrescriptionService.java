package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.PrescriptionRequestDTO;
import com.ph.Pharmacy.dto.response.PrescriptionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrescriptionService {

    PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO requestDTO, MultipartFile imageFile);

    PrescriptionResponseDTO getPrescriptionById(Long prescriptionId);

    Page<PrescriptionResponseDTO> getAllPrescriptions(Long userId, int page, int size, String sortBy, String sortDirection);

    Page<PrescriptionResponseDTO> getAllOrders(int page, int size, String sortBy, String sortDirection);

    Page<PrescriptionResponseDTO> getPrescriptionsByStatus(String status, int page, int size, String sortBy, String sortDirection);

    PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO requestDTO, MultipartFile imageFile, Long userId);

    PrescriptionResponseDTO updateApprovalStatus(Long id, Boolean isApproved);

    PrescriptionResponseDTO updateOrderStatus(Long id, String status);

    PrescriptionResponseDTO rejectOrder(Long id);

    byte[] getPrescriptionImage(Long id, Long userId);

    void deletePrescription(Long prescriptionId);
}