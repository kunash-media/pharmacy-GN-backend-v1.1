package com.gn.pharmacy.controller;



import com.gn.pharmacy.dto.request.PayRequestDto;
import com.gn.pharmacy.dto.response.PayResponseDto;
import com.gn.pharmacy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/create-Payment")
    public ResponseEntity<PayResponseDto> createPayment(@RequestBody PayRequestDto payRequestDto) {
        PayResponseDto response = payService.createPayment(payRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayResponseDto> getPaymentById(@PathVariable Long id) {
        PayResponseDto response = payService.getPaymentById(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PayResponseDto> getPaymentByOrderId(@PathVariable String orderId) {
        PayResponseDto response = payService.getPaymentByOrderId(orderId);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get-All-Payments")
    public ResponseEntity<List<PayResponseDto>> getAllPayments() {
        List<PayResponseDto> response = payService.getAllPayments();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayResponseDto> updatePayment(@PathVariable Long id, @RequestBody PayRequestDto payRequestDto) {
        PayResponseDto response = payService.updatePayment(id, payRequestDto);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        payService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

