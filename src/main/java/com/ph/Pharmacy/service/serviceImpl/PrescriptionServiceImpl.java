package com.ph.Pharmacy.service.serviceImpl;


import com.ph.Pharmacy.dto.request.PrescriptionRequestDTO;
import com.ph.Pharmacy.dto.response.PrescriptionResponseDTO;
import com.ph.Pharmacy.entity.PrescriptionEntity;
import com.ph.Pharmacy.entity.UserEntity;
import com.ph.Pharmacy.repository.PrescriptionRepository;
import com.ph.Pharmacy.repository.UserRepository;
import com.ph.Pharmacy.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   UserRepository userRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO requestDTO, MultipartFile imageFile) {
        log.info("Creating new prescription for userId: {}", requestDTO.getUserId());

        if (imageFile != null && !imageFile.isEmpty()) {
            log.info("Image file received: name={}, size={} bytes, contentType={}",
                    imageFile.getOriginalFilename(),
                    imageFile.getSize(),
                    imageFile.getContentType());
        } else {
            log.warn("No image file received!");
        }

        try {
            UserEntity user = userRepository.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDTO.getUserId()));

            PrescriptionEntity entity = new PrescriptionEntity();
            mapToEntity(requestDTO, entity);
            entity.setUser(user);

            byte[] imageBytes = convertToByteArray(imageFile);
            if (imageBytes != null) {
                log.info("Converted image to byte array: {} bytes", imageBytes.length);
            }
            entity.setPrescriptionImg(imageBytes);

            PrescriptionEntity saved = prescriptionRepository.save(entity);
            log.info("Prescription created successfully with ID: {}", saved.getPrescriptionId());
            return mapToResponse(saved);
        } catch (IOException e) {
            log.error("Error creating prescription: Failed to process image", e);
            throw new RuntimeException("Failed to process image file");
        }
    }

    @Override
    public PrescriptionResponseDTO getPrescriptionById(Long prescriptionId) {
        log.info("Fetching prescription ID: {}", prescriptionId);
        PrescriptionEntity entity = prescriptionRepository.findByPrescriptionId(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found for ID: " + prescriptionId));
        log.info("Prescription fetched successfully for ID: {}", prescriptionId);
        return mapToResponse(entity);
    }

    @Override
    public Page<PrescriptionResponseDTO> getAllPrescriptions(Long userId, int page, int size, String sortBy, String sortDirection) {
        log.info("Fetching all prescriptions for userId: {} with pagination - page: {}, size: {}", userId, page, size);

        Sort sort = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PrescriptionEntity> entities = prescriptionRepository.findByUserUserId(userId, pageable);

        log.info("Fetched {} prescriptions for userId: {}", entities.getTotalElements(), userId);
        return entities.map(this::mapToResponse);
    }

    @Override
    public Page<PrescriptionResponseDTO> getAllOrders(int page, int size, String sortBy, String sortDirection) {
        log.info("Fetching all orders with pagination - page: {}, size: {}", page, size);

        Sort sort = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PrescriptionEntity> entities = prescriptionRepository.findAll(pageable);

        log.info("Fetched {} total orders", entities.getTotalElements());
        return entities.map(this::mapToResponse);
    }

    @Override
    public Page<PrescriptionResponseDTO> getPrescriptionsByStatus(String status, int page, int size, String sortBy, String sortDirection) {
        log.info("Fetching prescriptions by status: {} with pagination - page: {}, size: {}", status, page, size);

        Sort sort = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PrescriptionEntity> entities = prescriptionRepository.findByOrderStatus(status, pageable);

        log.info("Fetched {} prescriptions with status: {}", entities.getTotalElements(), status);
        return entities.map(this::mapToResponse);
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO updatePrescription(Long prescriptionId, PrescriptionRequestDTO requestDTO, MultipartFile imageFile, Long userId) {
        log.info("Updating prescription ID: {} for userId: {}", prescriptionId, userId);

        PrescriptionEntity entity = prescriptionRepository.findByUserUserIdAndPrescriptionId(userId, prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found for ID: " + prescriptionId + " and userId: " + userId));

        try {
            mapToEntity(requestDTO, entity);

            if (imageFile != null && !imageFile.isEmpty()) {
                entity.setPrescriptionImg(convertToByteArray(imageFile));
                log.info("Updated prescription image");
            }

            PrescriptionEntity updated = prescriptionRepository.save(entity);
            log.info("Prescription updated successfully with ID: {}", prescriptionId);
            return mapToResponse(updated);
        } catch (IOException e) {
            log.error("Error updating prescription: Failed to process image", e);
            throw new RuntimeException("Failed to process image file");
        }
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO updateApprovalStatus(Long prescriptionId, Boolean isApproved) {
        log.info("Updating approval status for prescription ID: {} to {}", prescriptionId, isApproved);

        PrescriptionEntity entity = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + prescriptionId));

        entity.setApproved(isApproved);
        PrescriptionEntity updated = prescriptionRepository.save(entity);

        log.info("Approval status updated successfully for prescription ID: {}", prescriptionId);
        return mapToResponse(updated);
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO updateOrderStatus(Long prescriptionId, String status) {
        log.info("Updating order status for prescription ID: {} to {}", prescriptionId, status);

        PrescriptionEntity entity = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + prescriptionId));

        entity.setOrderStatus(status);
        PrescriptionEntity updated = prescriptionRepository.save(entity);

        log.info("Order status updated successfully for prescription ID: {}", prescriptionId);
        return mapToResponse(updated);
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO rejectOrder(Long prescriptionId) {
        log.info("Rejecting order for prescription ID: {}", prescriptionId);

        PrescriptionEntity entity = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + prescriptionId));

        entity.setOrderStatus("REJECTED");
        entity.setApproved(false);
        PrescriptionEntity updated = prescriptionRepository.save(entity);

        log.info("Order rejected successfully for prescription ID: {}", prescriptionId);
        return mapToResponse(updated);
    }

    @Override
    public byte[] getPrescriptionImage(Long prescriptionId, Long userId) {
        log.info("Fetching prescription image for ID: {} and userId: {}", prescriptionId, userId);

        PrescriptionEntity entity = prescriptionRepository.findByUserUserIdAndPrescriptionId(userId, prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found for ID: " + prescriptionId + " and userId: " + userId));

        return entity.getPrescriptionImg();
    }

    @Override
    @Transactional
    public void deletePrescription(Long prescriptionId) {
        log.info("Deleting prescription ID: {}", prescriptionId);

        PrescriptionEntity entity = prescriptionRepository.findByPrescriptionId(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found for ID: " + prescriptionId));

        prescriptionRepository.delete(entity);
        log.info("Prescription deleted successfully with ID: {}", prescriptionId);
    }

    private void mapToEntity(PrescriptionRequestDTO dto, PrescriptionEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setEmail(dto.getEmail());
        entity.setOrderStatus(dto.getOrderStatus());
        entity.setPaymentMethod(dto.getPaymentMethod());
    }

    private PrescriptionResponseDTO mapToResponse(PrescriptionEntity entity) {
        PrescriptionResponseDTO dto = new PrescriptionResponseDTO();
        dto.setPrescriptionId(entity.getPrescriptionId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setEmail(entity.getEmail());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setImageUrl("/api/prescriptions/" + entity.getPrescriptionId() + "/image");
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setApproved(entity.isApproved());
        dto.setUserId(entity.getUser().getUserId());
        return dto;
    }

    private byte[] convertToByteArray(MultipartFile file) throws IOException {
        return file != null && !file.isEmpty() ? file.getBytes() : null;
    }
}