package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.PrescriptionRequestDto;
import com.ph.Pharmacy.dto.response.PrescriptionResponseDto;
import com.ph.Pharmacy.entity.PrescriptionEntity;
import com.ph.Pharmacy.repository.PrescriptionRepository;
import com.ph.Pharmacy.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public PrescriptionResponseDto createPrescription(PrescriptionRequestDto requestDto) {
        PrescriptionEntity entity = new PrescriptionEntity();
        entity.setId(requestDto.getId());
        entity.setPatientName(requestDto.getPatientName());
        entity.setDoctorName(requestDto.getDoctorName());
        entity.setDate(requestDto.getDate());
        entity.setStatus(requestDto.getStatus());
        entity.setPatientDetails(requestDto.getPatientDetails());
        entity.setDoctorDetails(requestDto.getDoctorDetails());
        entity.setMedicines(requestDto.getMedicines());
        entity.setNotes(requestDto.getNotes());

        List<byte[]> images = new ArrayList<>();
        if (requestDto.getImages() != null) {
            for (MultipartFile file : requestDto.getImages()) {
                try {
                    images.add(file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to process image");
                }
            }
        }
        entity.setImages(images);

        PrescriptionEntity savedEntity = prescriptionRepository.save(entity);
        return mapToResponseDto(savedEntity);
    }

    @Override
    public PrescriptionResponseDto getPrescription(String id) {
        PrescriptionEntity entity = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        return mapToResponseDto(entity);
    }

    @Override
    public List<PrescriptionResponseDto> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionResponseDto> getPrescriptionsByStatus(String status) {
        return prescriptionRepository.findByStatus(status).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionResponseDto> searchPrescriptions(String searchTerm) {
        return prescriptionRepository.findByPatientNameContainingIgnoreCase(searchTerm).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionResponseDto updatePrescriptionStatus(String id, String newStatus, String pharmacistNotes) {
        PrescriptionEntity entity = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        entity.setStatus(newStatus);
        entity.setNotes(entity.getNotes() + "\n" + pharmacistNotes);
        PrescriptionEntity updatedEntity = prescriptionRepository.save(entity);
        return mapToResponseDto(updatedEntity);
    }

    private PrescriptionResponseDto mapToResponseDto(PrescriptionEntity entity) {
        PrescriptionResponseDto responseDto = new PrescriptionResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setPatientName(entity.getPatientName());
        responseDto.setDoctorName(entity.getDoctorName());
        responseDto.setDate(entity.getDate());
        responseDto.setStatus(entity.getStatus());
        responseDto.setPatientDetails(entity.getPatientDetails());
        responseDto.setDoctorDetails(entity.getDoctorDetails());
        responseDto.setMedicines(entity.getMedicines());
        responseDto.setNotes(entity.getNotes());
        List<String> imageNames = new ArrayList<>();
        if (entity.getImages() != null) {
            for (int i = 0; i < entity.getImages().size(); i++) {
                imageNames.add("prescription_" + entity.getId() + "_" + (i + 1) + ".jpg");
            }
        }
        responseDto.setImageNames(imageNames);
        return responseDto;
    }
}

