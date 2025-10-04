package com.gn.pharmacy.service;

import com.gn.pharmacy.dto.request.AdminRequestDto;
import com.gn.pharmacy.dto.response.AdminResponseDto;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto getAdminById(Long id);

    AdminResponseDto getAdminByEmail(String email);

    AdminResponseDto getAdminByPhoneNumber(String phoneNumber);

    List<AdminResponseDto> getAllAdmins();

    AdminResponseDto updateAdmin(Long id, AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdminPartial(Long id, AdminRequestDto adminRequestDto);

    void deleteAdmin(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean changePassword(Long id, String oldPassword, String newPassword);
}

