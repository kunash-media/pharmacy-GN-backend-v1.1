package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.UserRequestDto;
import com.ph.Pharmacy.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    void deleteUser(Long userId);
}

