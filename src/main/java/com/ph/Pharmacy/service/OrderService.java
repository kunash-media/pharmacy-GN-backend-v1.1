package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.OrderRequestDto;
import com.ph.Pharmacy.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long orderId);
    Page<OrderResponseDto> getAllOrders(Pageable pageable);
    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);
    OrderResponseDto patchOrder(Long orderId, OrderRequestDto orderRequestDto);
    void deleteOrder(Long orderId);
}

