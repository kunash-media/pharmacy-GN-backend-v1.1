package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.PrescriptionOrderRequestDto;
import com.ph.Pharmacy.dto.response.PrescriptionOrderResponseDto;

import java.util.List;

public interface PrescriptionOrderService {

    PrescriptionOrderResponseDto createOrder(PrescriptionOrderRequestDto requestDto);

    PrescriptionOrderResponseDto getOrderById(Long id);

    List<PrescriptionOrderResponseDto> getAllOrders();

    List<PrescriptionOrderResponseDto> getOrdersByUserId(Long userId);

    List<PrescriptionOrderResponseDto> getOrdersByStatus(String status);

    List<PrescriptionOrderResponseDto> getOrdersByUserIdAndStatus(Long userId, String status);

    PrescriptionOrderResponseDto updateOrder(Long id, PrescriptionOrderRequestDto requestDto);

    PrescriptionOrderResponseDto updateOrderStatus(Long id, String status);

    void deleteOrder(Long id);

    PrescriptionOrderResponseDto getOrderByIdAndUserId(Long id, Long userId);

    List<PrescriptionOrderResponseDto> getOrdersByEmail(String email);

    List<PrescriptionOrderResponseDto> getOrdersByPhone(String phone);

    Long getOrderCountByUserId(Long userId);

    List<PrescriptionOrderResponseDto> getOrdersByPincode(String pincode);
}
