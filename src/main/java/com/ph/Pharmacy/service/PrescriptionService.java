package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.PrescriptionRequestDto;
import com.ph.Pharmacy.dto.response.PrescriptionResponseDto;

import java.util.List;

public interface PrescriptionService {
    PrescriptionResponseDto createPrescription(PrescriptionRequestDto requestDto);
    PrescriptionResponseDto getPrescription(String id);
    List<PrescriptionResponseDto> getAllPrescriptions();
    List<PrescriptionResponseDto> getPrescriptionsByStatus(String status);
    List<PrescriptionResponseDto> searchPrescriptions(String searchTerm);
    PrescriptionResponseDto updatePrescriptionStatus(String id, String newStatus, String pharmacistNotes);
}
