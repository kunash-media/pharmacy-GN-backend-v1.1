package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.bcrypt.BcryptEncoderConfig;
import com.ph.Pharmacy.dto.request.UserRequestDto;
import com.ph.Pharmacy.dto.response.UserResponseDto;
import com.ph.Pharmacy.entity.UserEntity;
import com.ph.Pharmacy.repository.UserRepository;
import com.ph.Pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BcryptEncoderConfig bcryptEncoderConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BcryptEncoderConfig bcryptEncoderConfig) {
        this.userRepository = userRepository;
        this.bcryptEncoderConfig = bcryptEncoderConfig;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        mapRequestToEntity(userRequestDto, userEntity);
        UserEntity savedEntity = userRepository.save(userEntity);
        return mapEntityToResponse(savedEntity);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return mapEntityToResponse(userEntity);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        mapRequestToEntity(userRequestDto, userEntity);
        UserEntity updatedEntity = userRepository.save(userEntity);
        return mapEntityToResponse(updatedEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private void mapRequestToEntity(UserRequestDto requestDto, UserEntity entity) {
        // Split name into firstName and lastName
        if (requestDto.getName() != null) {
            String[] nameParts = requestDto.getName().split(" ", 2);
            entity.setFirstName(nameParts[0]);
            entity.setLastName(nameParts.length > 1 ? nameParts[1] : "");
        }
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getWhatsappNumber());
        // Encrypt the password
        entity.setPassword(bcryptEncoderConfig.encode(requestDto.getPassword()));
        if (requestDto.getAddress() != null) {
            entity.setAddressLandmark(requestDto.getAddress().getLandmark());
            entity.setAddressArea(requestDto.getAddress().getArea());
            entity.setAddressCity(requestDto.getAddress().getCity());
            entity.setAddressPincode(requestDto.getAddress().getPincode());
            entity.setAddressState(requestDto.getAddress().getState());
            entity.setAddressCountry(requestDto.getAddress().getCountry());
            entity.setAddressType(requestDto.getAddress().getAddressType());
        }
    }

    private UserResponseDto mapEntityToResponse(UserEntity entity) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUserId(entity.getUserId());
        responseDto.setFirstName(entity.getFirstName());
        responseDto.setLastName(entity.getLastName());
        responseDto.setWhatsappNumber(entity.getPhone());
        responseDto.setEmail(entity.getEmail());
        responseDto.setAddressLandmark(entity.getAddressLandmark());
        responseDto.setAddressArea(entity.getAddressArea());
        responseDto.setAddressCity(entity.getAddressCity());
        responseDto.setAddressPincode(entity.getAddressPincode());
        responseDto.setAddressState(entity.getAddressState());
        responseDto.setAddressCountry(entity.getAddressCountry());
        responseDto.setAddressType(entity.getAddressType());
        return responseDto;
    }
}