package com.ph.Pharmacy.service;

import com.ph.Pharmacy.dto.request.ShippingAddressRequestDto;
import com.ph.Pharmacy.dto.response.ShippingAddressResponseDto;

import java.util.List;

public interface ShippingAddressService {

    ShippingAddressResponseDto createShippingAddress(ShippingAddressRequestDto requestDto);

    ShippingAddressResponseDto getShippingAddressById(Long shippingId);

    List<ShippingAddressResponseDto> getAllShippingAddresses();

    List<ShippingAddressResponseDto> getShippingAddressByUserId(Long userId);

    List<ShippingAddressResponseDto> getShippingAddressByCity(String city);

    List<ShippingAddressResponseDto> getShippingAddressByState(String state);

    List<ShippingAddressResponseDto> getShippingAddressByCountry(String country);

    List<ShippingAddressResponseDto> getShippingAddressByCustomerEmail(String customerEmail);

    ShippingAddressResponseDto updateShippingAddress(Long shippingId, ShippingAddressRequestDto requestDto);

    void deleteShippingAddress(Long shippingId);
}

