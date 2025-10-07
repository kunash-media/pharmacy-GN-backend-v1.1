package com.gn.pharmacy.service;


import com.gn.pharmacy.dto.request.PrescriptionRequestDTO;
import com.gn.pharmacy.dto.response.PrescriptionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PrescriptionService {

    PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO requestDTO, MultipartFile imageFile);

    PrescriptionResponseDTO getPrescriptionById(String prescriptionId);

    Page<PrescriptionResponseDTO> getAllPrescriptions(Long userId, int page, int size, String sortBy, String sortDirection);

    Page<PrescriptionResponseDTO> getAllOrders(int page, int size, String sortBy, String sortDirection);

    Page<PrescriptionResponseDTO> getPrescriptionsByStatus(String status, int page, int size, String sortBy, String sortDirection);

    PrescriptionResponseDTO updatePrescription(String prescriptionId, PrescriptionRequestDTO requestDTO, MultipartFile imageFile, Long userId);

    PrescriptionResponseDTO updateApprovalStatus(String prescriptionId, Boolean isApproved);

    PrescriptionResponseDTO updateOrderStatus(String prescriptionId, String status);

    PrescriptionResponseDTO rejectOrder(String prescriptionId);

    byte[] getPrescriptionImage(String prescriptionId, Long userId);

    void deletePrescription(String prescriptionId);
}