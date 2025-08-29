package com.ph.Pharmacy.repository;
import com.ph.Pharmacy.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByUserId(Long userId);
}
