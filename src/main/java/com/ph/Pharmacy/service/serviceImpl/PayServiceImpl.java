package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.PayRequestDto;
import com.ph.Pharmacy.dto.response.PayResponseDto;
import com.ph.Pharmacy.entity.PayEntity;
import com.ph.Pharmacy.repository.PayRepository;
import com.ph.Pharmacy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository payRepository;

    @Override
    public PayResponseDto createPayment(PayRequestDto payRequestDto) {
        PayEntity payEntity = new PayEntity(
                payRequestDto.getRazorpayOrderId(),
                payRequestDto.getRazorpayPaymentId(),
                payRequestDto.getRazorpaySignature(),
                payRequestDto.getAmount(),
                payRequestDto.getCurrency(),
                payRequestDto.getReceipt(),
                payRequestDto.getCustomerName(),
                payRequestDto.getCustomerEmail(),
                payRequestDto.getCustomerPhone(),
                payRequestDto.getStatus()
        );

        PayEntity savedEntity = payRepository.save(payEntity);
        return convertToResponseDto(savedEntity);
    }

    @Override
    public PayResponseDto getPaymentById(Long id) {
        PayEntity payEntity = payRepository.findById(id).orElse(null);
        return payEntity != null ? convertToResponseDto(payEntity) : null;
    }

    @Override
    public PayResponseDto getPaymentByOrderId(String orderId) {
        PayEntity payEntity = payRepository.findByRazorpayOrderId(orderId).orElse(null);
        return payEntity != null ? convertToResponseDto(payEntity) : null;
    }

    @Override
    public List<PayResponseDto> getAllPayments() {
        return payRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayResponseDto updatePayment(Long id, PayRequestDto payRequestDto) {
        PayEntity payEntity = payRepository.findById(id).orElse(null);
        if (payEntity != null) {
            payEntity.setRazorpayOrderId(payRequestDto.getRazorpayOrderId());
            payEntity.setRazorpayPaymentId(payRequestDto.getRazorpayPaymentId());
            payEntity.setRazorpaySignature(payRequestDto.getRazorpaySignature());
            payEntity.setAmount(payRequestDto.getAmount());
            payEntity.setCurrency(payRequestDto.getCurrency());
            payEntity.setReceipt(payRequestDto.getReceipt());
            payEntity.setCustomerName(payRequestDto.getCustomerName());
            payEntity.setCustomerEmail(payRequestDto.getCustomerEmail());
            payEntity.setCustomerPhone(payRequestDto.getCustomerPhone());
            payEntity.setStatus(payRequestDto.getStatus());
            payEntity.setUpdatedAt(LocalDateTime.now());

            PayEntity updatedEntity = payRepository.save(payEntity);
            return convertToResponseDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        payRepository.deleteById(id);
    }

    private PayResponseDto convertToResponseDto(PayEntity payEntity) {
        return new PayResponseDto(
                payEntity.getId(),
                payEntity.getRazorpayOrderId(),
                payEntity.getRazorpayPaymentId(),
                payEntity.getRazorpaySignature(),
                payEntity.getAmount(),
                payEntity.getCurrency(),
                payEntity.getReceipt(),
                payEntity.getCustomerName(),
                payEntity.getCustomerEmail(),
                payEntity.getCustomerPhone(),
                payEntity.getStatus(),
                payEntity.getCreatedAt(),
                payEntity.getUpdatedAt()
        );
    }
}

