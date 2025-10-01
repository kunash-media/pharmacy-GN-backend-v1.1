package com.ph.Pharmacy.controller;
import com.ph.Pharmacy.dto.request.BannerRequestDto;
import com.ph.Pharmacy.dto.response.BannerResponseDto;
import com.ph.Pharmacy.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {
    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerService bannerService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<BannerResponseDto> createBanner(@ModelAttribute BannerRequestDto requestDto) {
        try {
            logger.info("Received request to create banner for page: {}", requestDto.getPageName());
            BannerResponseDto responseDto = bannerService.createBanner(requestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating banner: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-Banner-By-Id/{id}")
    public ResponseEntity<BannerResponseDto> getBannerById(@PathVariable Long id) {
        try {
            logger.info("Fetching banner with ID: {}", id);
            BannerResponseDto responseDto = bannerService.getBannerById(id);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching banner with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-All-Banners")
    public ResponseEntity<List<BannerResponseDto>> getAllBanners() {
        try {
            logger.info("Fetching all banners");
            List<BannerResponseDto> banners = bannerService.getAllBanners();
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all banners: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<BannerResponseDto> updateBanner(@PathVariable Long id, @ModelAttribute BannerRequestDto requestDto) {
        try {
            logger.info("Updating banner with ID: {}", id);
            BannerResponseDto responseDto = bannerService.updateBanner(id, requestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating banner with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-Banner/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        try {
            logger.info("Deleting banner with ID: {}", id);
            bannerService.deleteBanner(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting banner with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

