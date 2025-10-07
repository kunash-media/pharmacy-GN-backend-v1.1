package com.gn.pharmacy.controller;
import com.gn.pharmacy.dto.request.BannerTextRequestDto;
import com.gn.pharmacy.dto.response.BannerResponseDto;
import com.gn.pharmacy.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {
    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerService bannerService;

    @PostMapping(value = "/create-banner", consumes = "multipart/form-data")
    public ResponseEntity<BannerResponseDto> createBanner(
            @RequestPart("textData") BannerTextRequestDto textData,
            @RequestPart(value = "bannerFileOne", required = false) List<MultipartFile> bannerFileOne,
            @RequestPart(value = "bannerFileTwo", required = false) MultipartFile bannerFileTwo,
            @RequestPart(value = "bannerFileThree", required = false) MultipartFile bannerFileThree,
            @RequestPart(value = "bannerFileFour", required = false) MultipartFile bannerFileFour) {
        try {
            logger.info("Received request to create banner for page: {}", textData.getPageName());
            BannerResponseDto responseDto = bannerService.createBanner(textData, bannerFileOne, bannerFileTwo, bannerFileThree, bannerFileFour);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating banner: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-banner-by-Id/{bannerId}")
    public ResponseEntity<BannerResponseDto> getBannerById(@PathVariable Long bannerId) {
        try {
            logger.info("Fetching banner with ID: {}", bannerId);
            BannerResponseDto responseDto = bannerService.getBannerById(bannerId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching banner with ID {}: {}", bannerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-banners")
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

    @PutMapping(value = "/update-banner-by-bannerId/{bannerId}", consumes = "multipart/form-data")
    public ResponseEntity<BannerResponseDto> updateBanner(
            @PathVariable Long bannerId,
            @RequestPart("textData") BannerTextRequestDto textData,
            @RequestPart(value = "bannerFileOne", required = false) List<MultipartFile> bannerFileOne,
            @RequestPart(value = "bannerFileTwo", required = false) MultipartFile bannerFileTwo,
            @RequestPart(value = "bannerFileThree", required = false) MultipartFile bannerFileThree,
            @RequestPart(value = "bannerFileFour", required = false) MultipartFile bannerFileFour) {
        try {
            logger.info("Updating banner with ID: {}", bannerId);
            BannerResponseDto responseDto = bannerService.updateBanner(bannerId, textData, bannerFileOne, bannerFileTwo, bannerFileThree, bannerFileFour);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating banner with ID {}: {}", bannerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/patch-banner-by-bannerId/{bannerId}", consumes = "multipart/form-data")
    public ResponseEntity<BannerResponseDto> patchBanner(
            @PathVariable Long bannerId,
            @RequestPart(value = "textData", required = false) BannerTextRequestDto textData,
            @RequestPart(value = "bannerFileOne", required = false) List<MultipartFile> bannerFileOne,
            @RequestPart(value = "bannerFileTwo", required = false) MultipartFile bannerFileTwo,
            @RequestPart(value = "bannerFileThree", required = false) MultipartFile bannerFileThree,
            @RequestPart(value = "bannerFileFour", required = false) MultipartFile bannerFileFour) {
        try {
            logger.info("Patching banner with ID: {}", bannerId);
            BannerResponseDto responseDto = bannerService.patchBanner(bannerId, textData, bannerFileOne, bannerFileTwo, bannerFileThree, bannerFileFour);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error patching banner with ID {}: {}", bannerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-banner-by-bannerId/{bannerId}")
    public ResponseEntity<String> deleteBanner(@PathVariable Long bannerId) {
        try {
            logger.info("Deleting banner with ID: {}", bannerId);
            bannerService.deleteBanner(bannerId);
            return new ResponseEntity<>("Banner Deleted Successfully!! with ID :" + bannerId,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting banner with ID {}: {}", bannerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // New: Serve subimages from bannerFileOne
    @GetMapping(value = "/{bannerId}/subimage/{index}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getSubImage(@PathVariable Long bannerId, @PathVariable int index) {
        try {
            logger.info("Fetching subimage {} for banner ID: {}", index, bannerId);
            byte[] image = bannerService.getSubImage(bannerId, index);
            if (image == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            logger.error("Error fetching subimage {} for banner {}: {}", index, bannerId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // New: Serve bannerFileTwo
    @GetMapping(value = "/{bannerId}/bannerFileTwo", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getBannerFileTwo(@PathVariable Long bannerId) {
        try {
            logger.info("Fetching bannerFileTwo for banner ID: {}", bannerId);
            byte[] image = bannerService.getBannerFile(bannerId, "two");
            if (image == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            logger.error("Error fetching bannerFileTwo for banner {}: {}", bannerId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // New: Serve bannerFileThree
    @GetMapping(value = "/{bannerId}/bannerFileThree", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getBannerFileThree(@PathVariable Long bannerId) {
        try {
            logger.info("Fetching bannerFileThree for banner ID: {}", bannerId);
            byte[] image = bannerService.getBannerFile(bannerId, "three");
            if (image == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            logger.error("Error fetching bannerFileThree for banner {}: {}", bannerId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // New: Serve bannerFileFour
    @GetMapping(value = "/{bannerId}/bannerFileFour", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getBannerFileFour(@PathVariable Long bannerId) {
        try {
            logger.info("Fetching bannerFileFour for banner ID: {}", bannerId);
            byte[] image = bannerService.getBannerFile(bannerId, "four");
            if (image == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            logger.error("Error fetching bannerFileFour for banner {}: {}", bannerId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}