package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.ShippingAddressRequestDto;
import com.ph.Pharmacy.dto.response.ShippingAddressResponseDto;
import com.ph.Pharmacy.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping("/create-shipping-address")
    public ResponseEntity<ShippingAddressResponseDto> createShippingAddress(@RequestBody ShippingAddressRequestDto requestDto) {
        ShippingAddressResponseDto responseDto = shippingAddressService.createShippingAddress(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-shipping-address-by-id/{id}")
    public ResponseEntity<ShippingAddressResponseDto> getShippingAddressById(@PathVariable Long id) {
        ShippingAddressResponseDto responseDto = shippingAddressService.getShippingAddressById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get-all-shipping-addresses")
    public ResponseEntity<List<ShippingAddressResponseDto>> getAllShippingAddresses() {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getAllShippingAddresses();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-shipping-address-by-user-id/user/{userId}")
    public ResponseEntity<List<ShippingAddressResponseDto>> getShippingAddressByUserId(@PathVariable Long userId) {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getShippingAddressByUserId(userId);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-shipping-address-by-city/{city}")
    public ResponseEntity<List<ShippingAddressResponseDto>> getShippingAddressByCity(@PathVariable String city) {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getShippingAddressByCity(city);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-shipping-address-by-state/{state}")
    public ResponseEntity<List<ShippingAddressResponseDto>> getShippingAddressByState(@PathVariable String state) {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getShippingAddressByState(state);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-shipping-address-by-country/{country}")
    public ResponseEntity<List<ShippingAddressResponseDto>> getShippingAddressByCountry(@PathVariable String country) {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getShippingAddressByCountry(country);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-shipping-address-by-customer-email/{email}")
    public ResponseEntity<List<ShippingAddressResponseDto>> getShippingAddressByCustomerEmail(@PathVariable String email) {
        List<ShippingAddressResponseDto> responseDtos = shippingAddressService.getShippingAddressByCustomerEmail(email);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping("/update-shipping-address/{id}")
    public ResponseEntity<ShippingAddressResponseDto> updateShippingAddress(@PathVariable Long id, @RequestBody ShippingAddressRequestDto requestDto) {
        ShippingAddressResponseDto responseDto = shippingAddressService.updateShippingAddress(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete-shipping-address/{id}")
    public ResponseEntity<String> deleteShippingAddress(@PathVariable Long id) {
        shippingAddressService.deleteShippingAddress(id);
        return new ResponseEntity<>("Shipping address deleted successfully", HttpStatus.OK);
    }
}

