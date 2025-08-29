package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.PrescriptionOrderRequestDto;
import com.ph.Pharmacy.dto.response.PrescriptionOrderResponseDto;
import com.ph.Pharmacy.entity.PrescriptionOrderEntity;
import com.ph.Pharmacy.repository.PrescriptionOrderRepository;
import com.ph.Pharmacy.service.PrescriptionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionOrderServiceImpl implements PrescriptionOrderService {

    @Autowired
    private PrescriptionOrderRepository prescriptionOrderRepository;

    @Override
    public PrescriptionOrderResponseDto createOrder(PrescriptionOrderRequestDto requestDto) {
        PrescriptionOrderEntity entity = convertToEntity(requestDto);
        PrescriptionOrderEntity savedEntity = prescriptionOrderRepository.save(entity);
        return new PrescriptionOrderResponseDto(savedEntity);
    }

    @Override
    public PrescriptionOrderResponseDto getOrderById(Long id) {
        PrescriptionOrderEntity entity = prescriptionOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return new PrescriptionOrderResponseDto(entity);
    }

    @Override
    public List<PrescriptionOrderResponseDto> getAllOrders() {
        return prescriptionOrderRepository.findAll()
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByUserId(Long userId) {
        return prescriptionOrderRepository.findByUserId(userId)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByStatus(String status) {
        PrescriptionOrderEntity.OrderStatus orderStatus = PrescriptionOrderEntity.OrderStatus.valueOf(status.toUpperCase());
        return prescriptionOrderRepository.findByOrderStatus(orderStatus)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByUserIdAndStatus(Long userId, String status) {
        PrescriptionOrderEntity.OrderStatus orderStatus = PrescriptionOrderEntity.OrderStatus.valueOf(status.toUpperCase());
        return prescriptionOrderRepository.findByUserIdAndStatus(userId, orderStatus)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionOrderResponseDto updateOrder(Long id, PrescriptionOrderRequestDto requestDto) {
        PrescriptionOrderEntity existingEntity = prescriptionOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        updateEntityFromDto(existingEntity, requestDto);
        PrescriptionOrderEntity updatedEntity = prescriptionOrderRepository.save(existingEntity);
        return new PrescriptionOrderResponseDto(updatedEntity);
    }

    @Override
    public PrescriptionOrderResponseDto updateOrderStatus(Long id, String status) {
        PrescriptionOrderEntity entity = prescriptionOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        PrescriptionOrderEntity.OrderStatus orderStatus = PrescriptionOrderEntity.OrderStatus.valueOf(status.toUpperCase());
        entity.setOrderStatus(orderStatus);

        PrescriptionOrderEntity updatedEntity = prescriptionOrderRepository.save(entity);
        return new PrescriptionOrderResponseDto(updatedEntity);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!prescriptionOrderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        prescriptionOrderRepository.deleteById(id);
    }

    @Override
    public PrescriptionOrderResponseDto getOrderByIdAndUserId(Long id, Long userId) {
        PrescriptionOrderEntity entity = prescriptionOrderRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id + " for user: " + userId));
        return new PrescriptionOrderResponseDto(entity);
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByEmail(String email) {
        return prescriptionOrderRepository.findByShippingEmail(email)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByPhone(String phone) {
        return prescriptionOrderRepository.findByShippingPhone(phone)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long getOrderCountByUserId(Long userId) {
        return prescriptionOrderRepository.countOrdersByUserId(userId);
    }

    @Override
    public List<PrescriptionOrderResponseDto> getOrdersByPincode(String pincode) {
        return prescriptionOrderRepository.findByShippingPincode(pincode)
                .stream()
                .map(PrescriptionOrderResponseDto::new)
                .collect(Collectors.toList());
    }

    private PrescriptionOrderEntity convertToEntity(PrescriptionOrderRequestDto dto) {
        PrescriptionOrderEntity entity = new PrescriptionOrderEntity();

        entity.setUserId(dto.getUserId());
        entity.setPrescriptionImageUrl(dto.getPrescriptionImageUrl());
        entity.setShippingAddress(dto.getShippingAddress());
        entity.setShippingAddress2(dto.getShippingAddress2());
        entity.setShippingCity(dto.getShippingCity());
        entity.setShippingState(dto.getShippingState());
        entity.setShippingPincode(dto.getShippingPincode());
        entity.setShippingCountry(dto.getShippingCountry());
        entity.setShippingFirstName(dto.getShippingFirstName());
        entity.setShippingLastName(dto.getShippingLastName());
        entity.setShippingEmail(dto.getShippingEmail());
        entity.setShippingPhone(dto.getShippingPhone());
        entity.setShippingIsBilling(dto.getShippingIsBilling());
        entity.setBillingCustomerName(dto.getBillingCustomerName());
        entity.setBillingLastName(dto.getBillingLastName());
        entity.setBillingAddress(dto.getBillingAddress());
        entity.setBillingAddress2(dto.getBillingAddress2());
        entity.setBillingCity(dto.getBillingCity());
        entity.setBillingPincode(dto.getBillingPincode());
        entity.setBillingState(dto.getBillingState());
        entity.setBillingCountry(dto.getBillingCountry());
        entity.setBillingEmail(dto.getBillingEmail());
        entity.setBillingPhone(dto.getBillingPhone());

        return entity;
    }

    private void updateEntityFromDto(PrescriptionOrderEntity entity, PrescriptionOrderRequestDto dto) {
        if (dto.getUserId() != null) {
            entity.setUserId(dto.getUserId());
        }
        if (dto.getPrescriptionImageUrl() != null) {
            entity.setPrescriptionImageUrl(dto.getPrescriptionImageUrl());
        }
        if (dto.getShippingAddress() != null) {
            entity.setShippingAddress(dto.getShippingAddress());
        }
        if (dto.getShippingAddress2() != null) {
            entity.setShippingAddress2(dto.getShippingAddress2());
        }
        if (dto.getShippingCity() != null) {
            entity.setShippingCity(dto.getShippingCity());
        }
        if (dto.getShippingState() != null) {
            entity.setShippingState(dto.getShippingState());
        }
        if (dto.getShippingPincode() != null) {
            entity.setShippingPincode(dto.getShippingPincode());
        }
        if (dto.getShippingCountry() != null) {
            entity.setShippingCountry(dto.getShippingCountry());
        }
        if (dto.getShippingFirstName() != null) {
            entity.setShippingFirstName(dto.getShippingFirstName());
        }
        if (dto.getShippingLastName() != null) {
            entity.setShippingLastName(dto.getShippingLastName());
        }
        if (dto.getShippingEmail() != null) {
            entity.setShippingEmail(dto.getShippingEmail());
        }
        if (dto.getShippingPhone() != null) {
            entity.setShippingPhone(dto.getShippingPhone());
        }
        if (dto.getShippingIsBilling() != null) {
            entity.setShippingIsBilling(dto.getShippingIsBilling());
        }
        if (dto.getBillingCustomerName() != null) {
            entity.setBillingCustomerName(dto.getBillingCustomerName());
        }
        if (dto.getBillingLastName() != null) {
            entity.setBillingLastName(dto.getBillingLastName());
        }
        if (dto.getBillingAddress() != null) {
            entity.setBillingAddress(dto.getBillingAddress());
        }
        if (dto.getBillingAddress2() != null) {
            entity.setBillingAddress2(dto.getBillingAddress2());
        }
        if (dto.getBillingCity() != null) {
            entity.setBillingCity(dto.getBillingCity());
        }
        if (dto.getBillingPincode() != null) {
            entity.setBillingPincode(dto.getBillingPincode());
        }
        if (dto.getBillingState() != null) {
            entity.setBillingState(dto.getBillingState());
        }
        if (dto.getBillingCountry() != null) {
            entity.setBillingCountry(dto.getBillingCountry());
        }
        if (dto.getBillingEmail() != null) {
            entity.setBillingEmail(dto.getBillingEmail());
        }
        if (dto.getBillingPhone() != null) {
            entity.setBillingPhone(dto.getBillingPhone());
        }
    }
}
