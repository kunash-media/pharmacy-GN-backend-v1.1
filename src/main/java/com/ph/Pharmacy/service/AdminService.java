package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.AdminRequestDto;
import com.ph.Pharmacy.dto.response.AdminResponseDto;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto getAdminById(Long id);

    AdminResponseDto getAdminByEmail(String email);

    AdminResponseDto getAdminByPhoneNumber(String phoneNumber);

    List<AdminResponseDto> getAllAdmins();

    List<AdminResponseDto> getAdminsByFirstName(String firstName);

    List<AdminResponseDto> getAdminsByLastName(String lastName);

    List<AdminResponseDto> getAdminsByFullName(String firstName, String lastName);

    List<AdminResponseDto> getAdminsByEmailDomain(String domain);

    List<AdminResponseDto> getAdminsByPhonePattern(String pattern);

    AdminResponseDto updateAdmin(Long id, AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdminPartial(Long id, AdminRequestDto adminRequestDto);

    void deleteAdmin(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}

