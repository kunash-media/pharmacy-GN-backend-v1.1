package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.PrescriptionOrderRequestDto;
import com.ph.Pharmacy.dto.response.PrescriptionOrderResponseDto;
import com.ph.Pharmacy.service.PrescriptionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription-orders")
@CrossOrigin(origins = "*")
public class PrescriptionOrderController {

    @Autowired
    private PrescriptionOrderService prescriptionOrderService;

    @PostMapping("/create-Order")
    public ResponseEntity<PrescriptionOrderResponseDto> createOrder(@RequestBody PrescriptionOrderRequestDto requestDto) {
        PrescriptionOrderResponseDto response = prescriptionOrderService.createOrder(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionOrderResponseDto> getOrderById(@PathVariable Long id) {
        PrescriptionOrderResponseDto response = prescriptionOrderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-All-Orders")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getAllOrders() {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByStatus(@PathVariable String status) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByUserIdAndStatus(
            @PathVariable Long userId,
            @PathVariable String status) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionOrderResponseDto> updateOrder(
            @PathVariable Long id,
            @RequestBody PrescriptionOrderRequestDto requestDto) {
        PrescriptionOrderResponseDto response = prescriptionOrderService.updateOrder(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PrescriptionOrderResponseDto> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        PrescriptionOrderResponseDto response = prescriptionOrderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        prescriptionOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/user/{userId}")
    public ResponseEntity<PrescriptionOrderResponseDto> getOrderByIdAndUserId(
            @PathVariable Long id,
            @PathVariable Long userId) {
        PrescriptionOrderResponseDto response = prescriptionOrderService.getOrderByIdAndUserId(id, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/email")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByEmail(@RequestParam String email) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/phone")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByPhone(@RequestParam String phone) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByPhone(phone);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/user/{userId}")
    public ResponseEntity<Long> getOrderCountByUserId(@PathVariable Long userId) {
        Long count = prescriptionOrderService.getOrderCountByUserId(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/search/pincode")
    public ResponseEntity<List<PrescriptionOrderResponseDto>> getOrdersByPincode(@RequestParam String pincode) {
        List<PrescriptionOrderResponseDto> response = prescriptionOrderService.getOrdersByPincode(pincode);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
