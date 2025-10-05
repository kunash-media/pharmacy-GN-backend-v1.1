package com.gn.pharmacy.service.serviceImpl;

import com.gn.pharmacy.dto.request.WishlistRequestDto;
import com.gn.pharmacy.dto.response.WishlistResponseDto;
import com.gn.pharmacy.entity.WishlistEntity;
import com.gn.pharmacy.repository.WishlistRepository;
import com.gn.pharmacy.service.WishlistService;
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
//    @PostConstruct
//    public void init() {
//        List<WishlistEntity> dummyData = new ArrayList<>();
//        dummyData.add(new WishlistEntity(1L, 101L));
//        dummyData.add(new WishlistEntity(1L, 102L));
//        dummyData.add(new WishlistEntity(2L, 103L));
//        wishlistRepository.saveAll(dummyData);
//    }

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
