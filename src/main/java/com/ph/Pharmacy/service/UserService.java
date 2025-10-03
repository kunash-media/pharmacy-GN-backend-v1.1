package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.UserRequestDto;
import com.ph.Pharmacy.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    void deleteUser(Long userId);

    public UserResponseDto patchUser(@PathVariable Long userId,
                                                     @RequestBody UserRequestDto userRequestDto);

    boolean isEmailExists(String email);
}

