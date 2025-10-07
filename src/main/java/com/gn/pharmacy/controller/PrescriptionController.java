package com.gn.pharmacy.controller;


import com.gn.pharmacy.dto.request.PrescriptionRequestDTO;
import com.gn.pharmacy.dto.response.PrescriptionResponseDTO;
import com.gn.pharmacy.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/prescriptions")
@Slf4j
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService){
        this.prescriptionService = prescriptionService;
    }

    @PostMapping(value = "/create-order", consumes = {"multipart/form-data"})
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(
            @RequestPart("orderData") PrescriptionRequestDTO requestDTO,
            @RequestPart(value = "prescriptionImg", required = false) MultipartFile prescriptionImg) {
        log.info("Received create request for prescription");
        PrescriptionResponseDTO response = prescriptionService.createPrescription(requestDTO, prescriptionImg);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-prescriptionId/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(
            @PathVariable String prescriptionId) {
        log.info("Received get request for prescription ID: {}", prescriptionId);
        PrescriptionResponseDTO response = prescriptionService.getPrescriptionById(prescriptionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-orders-by-userId/{userId}")
    public ResponseEntity<Page<PrescriptionResponseDTO>> getAllPrescriptions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        log.info("Received get all request for userId: {} with page: {}, size: {}", userId, page, size);
        Page<PrescriptionResponseDTO> responses = prescriptionService.getAllPrescriptions(userId, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<Page<PrescriptionResponseDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        log.info("Received get all orders request with page: {}, size: {}", page, size);
        Page<PrescriptionResponseDTO> responses = prescriptionService.getAllOrders(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get-by-status")
    public ResponseEntity<Page<PrescriptionResponseDTO>> getPrescriptionsByStatus(
            @RequestParam("status") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        log.info("Received get by status request for status: {} with page: {}, size: {}", status, page, size);
        Page<PrescriptionResponseDTO> responses = prescriptionService.getPrescriptionsByStatus(status, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(responses);
    }

    @PutMapping(value = "/update-by-prescriptionId/{prescriptionId}", consumes = {"multipart/form-data"})
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(
            @PathVariable String prescriptionId,
            @RequestPart("orderData") PrescriptionRequestDTO requestDTO,
            @RequestPart(value = "prescriptionImg", required = false) MultipartFile prescriptionImg,
            @RequestParam Long userId) {
        log.info("Received update request for prescription ID: {} for userId: {}", prescriptionId, userId);
        PrescriptionResponseDTO response = prescriptionService.updatePrescription(prescriptionId, requestDTO, prescriptionImg, userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{prescriptionId}/approve")
    public ResponseEntity<PrescriptionResponseDTO> approvePrescription(
            @PathVariable String prescriptionId,
            @RequestParam Boolean isApproved) {
        log.info("Received approval request for prescription ID: {} with approval: {}", prescriptionId, isApproved);
        PrescriptionResponseDTO response = prescriptionService.updateApprovalStatus(prescriptionId, isApproved);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch-status/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDTO> updateOrderStatus(
            @PathVariable String prescriptionId,
            @RequestParam("status") String status) {
        log.info("Received status update request for prescription ID: {} to status: {}", prescriptionId, status);
        PrescriptionResponseDTO response = prescriptionService.updateOrderStatus(prescriptionId, status);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/reject-order-by-status/{prescriptionId}/reject-order")
    public ResponseEntity<PrescriptionResponseDTO> rejectOrder(@PathVariable String prescriptionId) {
        log.info("Received reject order request for prescription ID: {}", prescriptionId);
        PrescriptionResponseDTO response = prescriptionService.rejectOrder(prescriptionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{prescriptionId}/image")
    public ResponseEntity<byte[]> getPrescriptionImage(
            @PathVariable String prescriptionId,
            @RequestParam Long userId) {
        log.info("Fetching prescription image for ID: {} and userId: {}", prescriptionId, userId);

        byte[] image = prescriptionService.getPrescriptionImage(prescriptionId, userId);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @DeleteMapping("/delete-order-by-prescriptionId/{prescriptionId}")
    public ResponseEntity<String> deletePrescription(
            @PathVariable String prescriptionId) {
        log.info("Received delete request for prescription ID: {}", prescriptionId);
        prescriptionService.deletePrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body("Order Deleted Successfully!! with id : " + prescriptionId);
    }
}