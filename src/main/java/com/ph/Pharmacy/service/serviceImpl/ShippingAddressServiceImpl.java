package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.ShippingAddressRequestDto;
import com.ph.Pharmacy.dto.response.ShippingAddressResponseDto;
import com.ph.Pharmacy.entity.ShippingAddressEntity;
import com.ph.Pharmacy.repository.ShippingAddressRepository;
import com.ph.Pharmacy.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddressResponseDto createShippingAddress(ShippingAddressRequestDto requestDto) {
        ShippingAddressEntity entity = convertToEntity(requestDto);
        ShippingAddressEntity savedEntity = shippingAddressRepository.save(entity);
        return convertToResponseDto(savedEntity);
    }

    @Override
    public ShippingAddressResponseDto getShippingAddressById(Long shippingId) {
        ShippingAddressEntity entity = shippingAddressRepository.findById(shippingId)
                .orElseThrow(() -> new RuntimeException("Shipping address not found with id: " + shippingId));
        return convertToResponseDto(entity);
    }

    @Override
    public List<ShippingAddressResponseDto> getAllShippingAddresses() {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findAll();
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressByUserId(Long userId) {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findByUserId(userId);
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressByCity(String city) {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findByShippingCity(city);
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressByState(String state) {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findByShippingState(state);
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressByCountry(String country) {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findByShippingCountry(country);
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressByCustomerEmail(String customerEmail) {
        List<ShippingAddressEntity> entities = shippingAddressRepository.findByCustomerEmail(customerEmail);
        return entities.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public ShippingAddressResponseDto updateShippingAddress(Long shippingId, ShippingAddressRequestDto requestDto) {
        ShippingAddressEntity existingEntity = shippingAddressRepository.findById(shippingId)
                .orElseThrow(() -> new RuntimeException("Shipping address not found with id: " + shippingId));

        updateEntityFromDto(existingEntity, requestDto);
        ShippingAddressEntity updatedEntity = shippingAddressRepository.save(existingEntity);
        return convertToResponseDto(updatedEntity);
    }

    @Override
    public void deleteShippingAddress(Long shippingId) {
        if (!shippingAddressRepository.existsById(shippingId)) {
            throw new RuntimeException("Shipping address not found with id: " + shippingId);
        }
        shippingAddressRepository.deleteById(shippingId);
    }

    // Helper methods for conversion
    private ShippingAddressEntity convertToEntity(ShippingAddressRequestDto requestDto) {
        return new ShippingAddressEntity(
                requestDto.getUserId(),
                requestDto.getUsername(),
                requestDto.getEmail(),
                requestDto.getCustomerPhone(),
                requestDto.getCustomerEmail(),
                requestDto.getShippingAddress(),
                requestDto.getShippingCity(),
                requestDto.getShippingState(),
                requestDto.getShippingPincode(),
                requestDto.getShippingCountry()
        );
    }

    private ShippingAddressResponseDto convertToResponseDto(ShippingAddressEntity entity) {
        ShippingAddressResponseDto.UserDto userDto = new ShippingAddressResponseDto.UserDto(
                entity.getUserId(),
                entity.getUsername(),
                entity.getEmail()
        );

        return new ShippingAddressResponseDto(
                entity.getShippingId(),
                userDto,
                entity.getCustomerPhone(),
                entity.getCustomerEmail(),
                entity.getShippingAddress(),
                entity.getShippingCity(),
                entity.getShippingState(),
                entity.getShippingPincode(),
                entity.getShippingCountry()
        );
    }

    private void updateEntityFromDto(ShippingAddressEntity entity, ShippingAddressRequestDto requestDto) {
        entity.setUserId(requestDto.getUserId());
        entity.setUsername(requestDto.getUsername());
        entity.setEmail(requestDto.getEmail());
        entity.setCustomerPhone(requestDto.getCustomerPhone());
        entity.setCustomerEmail(requestDto.getCustomerEmail());
        entity.setShippingAddress(requestDto.getShippingAddress());
        entity.setShippingCity(requestDto.getShippingCity());
        entity.setShippingState(requestDto.getShippingState());
        entity.setShippingPincode(requestDto.getShippingPincode());
        entity.setShippingCountry(requestDto.getShippingCountry());
    }
}

