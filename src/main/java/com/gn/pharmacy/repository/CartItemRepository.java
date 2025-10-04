package com.gn.pharmacy.repository;
import com.gn.pharmacy.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByUserId(Long userId);
}
