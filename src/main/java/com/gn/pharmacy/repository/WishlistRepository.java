package com.gn.pharmacy.repository;

import com.gn.pharmacy.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
    List<WishlistEntity> findByUserId(Long userId);
}
