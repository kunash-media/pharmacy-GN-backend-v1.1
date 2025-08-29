package com.ph.Pharmacy.repository;

import com.ph.Pharmacy.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Long> {

    List<ShippingAddressEntity> findByUserId(Long userId);

    List<ShippingAddressEntity> findByShippingCity(String city);

    List<ShippingAddressEntity> findByShippingState(String state);

    List<ShippingAddressEntity> findByShippingCountry(String country);

    List<ShippingAddressEntity> findByCustomerEmail(String customerEmail);
}

