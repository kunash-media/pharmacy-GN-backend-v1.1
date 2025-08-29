package com.ph.Pharmacy.service;


import com.ph.Pharmacy.dto.request.PayRequestDto;
import com.ph.Pharmacy.dto.response.PayResponseDto;

import java.util.List;

public interface PayService {

    PayResponseDto createPayment(PayRequestDto payRequestDto);
    PayResponseDto getPaymentById(Long id);
    PayResponseDto getPaymentByOrderId(String orderId);
    List<PayResponseDto> getAllPayments();
    PayResponseDto updatePayment(Long id, PayRequestDto payRequestDto);
    void deletePayment(Long id);
}

