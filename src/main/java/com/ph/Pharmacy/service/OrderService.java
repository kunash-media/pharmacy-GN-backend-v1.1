package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.OrderRequestDto;
import com.ph.Pharmacy.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long orderId);
    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);
    void deleteOrder(Long orderId);
}

