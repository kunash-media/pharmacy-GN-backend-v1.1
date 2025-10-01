package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.PrescriptionRequestDto;
import com.ph.Pharmacy.dto.request.StatusUpdateDto;
import com.ph.Pharmacy.dto.response.PrescriptionResponseDto;
import com.ph.Pharmacy.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<PrescriptionResponseDto> createPrescription(
            @RequestPart("prescription") PrescriptionRequestDto requestDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        requestDto.setImages(images);
        PrescriptionResponseDto responseDto = prescriptionService.createPrescription(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-Prescription/{id}")
    public ResponseEntity<PrescriptionResponseDto> getPrescription(@PathVariable String id) {
        return ResponseEntity.ok(prescriptionService.getPrescription(id));
    }

    @GetMapping("/get-All-Prescriptions")
    public ResponseEntity<List<PrescriptionResponseDto>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/get-Prescriptions-By-Status/status/{status}")
    public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByStatus(status));
    }

    @GetMapping("/search-Prescriptions/search")
    public ResponseEntity<List<PrescriptionResponseDto>> searchPrescriptions(@RequestParam String searchTerm) {
        return ResponseEntity.ok(prescriptionService.searchPrescriptions(searchTerm));
    }

    @PutMapping("/update-Prescription-Status/{id}/status")
    public ResponseEntity<PrescriptionResponseDto> updatePrescriptionStatus(
            @PathVariable String id,
            @RequestBody StatusUpdateDto statusUpdate) {
        return ResponseEntity.ok(prescriptionService.updatePrescriptionStatus(
                id, statusUpdate.getNewStatus(), statusUpdate.getPharmacistNotes()));
    }
}

