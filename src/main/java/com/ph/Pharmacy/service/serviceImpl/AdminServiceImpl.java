package com.ph.Pharmacy.service.serviceImpl;


import com.ph.Pharmacy.dto.request.AdminRequestDto;
import com.ph.Pharmacy.dto.response.AdminResponseDto;
import com.ph.Pharmacy.entity.AdminEntity;
import com.ph.Pharmacy.repository.AdminRepository;
import com.ph.Pharmacy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {
        AdminEntity adminEntity = convertToEntity(adminRequestDto);
        AdminEntity savedAdmin = adminRepository.save(adminEntity);
        return convertToResponseDto(savedAdmin);
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        AdminEntity adminEntity = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        return convertToResponseDto(adminEntity);
    }

    @Override
    public AdminResponseDto getAdminByEmail(String email) {
        AdminEntity adminEntity = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));
        return convertToResponseDto(adminEntity);
    }

    @Override
    public AdminResponseDto getAdminByPhoneNumber(String phoneNumber) {
        AdminEntity adminEntity = adminRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Admin not found with phone number: " + phoneNumber));
        return convertToResponseDto(adminEntity);
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        List<AdminEntity> admins = adminRepository.findAll();
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDto> getAdminsByFirstName(String firstName) {
        List<AdminEntity> admins = adminRepository.findByFirstName(firstName);
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDto> getAdminsByLastName(String lastName) {
        List<AdminEntity> admins = adminRepository.findByLastName(lastName);
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDto> getAdminsByFullName(String firstName, String lastName) {
        List<AdminEntity> admins = adminRepository.findByFirstNameAndLastName(firstName, lastName);
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDto> getAdminsByEmailDomain(String domain) {
        List<AdminEntity> admins = adminRepository.findByEmailDomain(domain);
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDto> getAdminsByPhonePattern(String pattern) {
        List<AdminEntity> admins = adminRepository.findByPhoneNumberPattern(pattern);
        return admins.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDto updateAdmin(Long id, AdminRequestDto adminRequestDto) {
        AdminEntity existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        // Update all fields
        existingAdmin.setFirstName(adminRequestDto.getFirstName());
        existingAdmin.setLastName(adminRequestDto.getLastName());
        existingAdmin.setPhoneNumber(adminRequestDto.getPhoneNumber());
        existingAdmin.setEmail(adminRequestDto.getEmail());
        existingAdmin.setPassword(adminRequestDto.getPassword());
        existingAdmin.setConfirmPassword(adminRequestDto.getConfirmPassword());

        AdminEntity updatedAdmin = adminRepository.save(existingAdmin);
        return convertToResponseDto(updatedAdmin);
    }

    @Override
    public AdminResponseDto updateAdminPartial(Long id, AdminRequestDto adminRequestDto) {
        AdminEntity existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        // Update only non-null fields
        if (adminRequestDto.getFirstName() != null) {
            existingAdmin.setFirstName(adminRequestDto.getFirstName());
        }
        if (adminRequestDto.getLastName() != null) {
            existingAdmin.setLastName(adminRequestDto.getLastName());
        }
        if (adminRequestDto.getPhoneNumber() != null) {
            existingAdmin.setPhoneNumber(adminRequestDto.getPhoneNumber());
        }
        if (adminRequestDto.getEmail() != null) {
            existingAdmin.setEmail(adminRequestDto.getEmail());
        }
        if (adminRequestDto.getPassword() != null) {
            existingAdmin.setPassword(adminRequestDto.getPassword());
        }
        if (adminRequestDto.getConfirmPassword() != null) {
            existingAdmin.setConfirmPassword(adminRequestDto.getConfirmPassword());
        }

        AdminEntity updatedAdmin = adminRepository.save(existingAdmin);
        return convertToResponseDto(updatedAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return adminRepository.existsByPhoneNumber(phoneNumber);
    }

    // Helper method to convert RequestDto to Entity
    private AdminEntity convertToEntity(AdminRequestDto requestDto) {
        AdminEntity entity = new AdminEntity();
        entity.setFirstName(requestDto.getFirstName());
        entity.setLastName(requestDto.getLastName());
        entity.setPhoneNumber(requestDto.getPhoneNumber());
        entity.setEmail(requestDto.getEmail());
        entity.setPassword(requestDto.getPassword());
        entity.setConfirmPassword(requestDto.getConfirmPassword());
        return entity;
    }

    // Helper method to convert Entity to ResponseDto
    private AdminResponseDto convertToResponseDto(AdminEntity entity) {
        AdminResponseDto responseDto = new AdminResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setFirstName(entity.getFirstName());
        responseDto.setLastName(entity.getLastName());
        responseDto.setPhoneNumber(entity.getPhoneNumber());
        responseDto.setEmail(entity.getEmail());
        responseDto.setPassword(entity.getPassword());
        responseDto.setConfirmPassword(entity.getConfirmPassword());
        return responseDto;
    }
}

