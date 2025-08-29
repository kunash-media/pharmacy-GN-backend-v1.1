package com.ph.Pharmacy.service.serviceImpl;

import com.ph.Pharmacy.dto.request.WishlistRequestDto;
import com.ph.Pharmacy.dto.response.WishlistResponseDto;
import com.ph.Pharmacy.entity.WishlistEntity;
import com.ph.Pharmacy.repository.WishlistRepository;
import com.ph.Pharmacy.service.WishlistService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Initialize dummy data
    @PostConstruct
    public void init() {
        List<WishlistEntity> dummyData = new ArrayList<>();
        dummyData.add(new WishlistEntity(1L, 101L));
        dummyData.add(new WishlistEntity(1L, 102L));
        dummyData.add(new WishlistEntity(2L, 103L));
        wishlistRepository.saveAll(dummyData);
    }

    @Override
    public WishlistResponseDto addToWishlist(WishlistRequestDto requestDto) {
        WishlistEntity entity = new WishlistEntity(requestDto.getUserId(), requestDto.getProductId());
        WishlistEntity savedEntity = wishlistRepository.save(entity);
        return new WishlistResponseDto(savedEntity.getId(), savedEntity.getUserId(), savedEntity.getProductId());
    }

    @Override
    public List<WishlistResponseDto> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId)
                .stream()
                .map(entity -> new WishlistResponseDto(entity.getId(), entity.getUserId(), entity.getProductId()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeFromWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
