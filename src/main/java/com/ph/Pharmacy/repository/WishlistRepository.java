package com.ph.Pharmacy.repository;

import com.ph.Pharmacy.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
    List<WishlistEntity> findByUserId(Long userId);
}
