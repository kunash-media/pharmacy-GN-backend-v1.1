package com.gn.pharmacy.service.serviceImpl;
import com.gn.pharmacy.dto.request.BannerTextRequestDto;
import com.gn.pharmacy.dto.response.BannerResponseDto;
import com.gn.pharmacy.entity.BannerEntity;
import com.gn.pharmacy.repository.BannerRepository;
import com.gn.pharmacy.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {

    private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public BannerResponseDto createBanner(BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception {

        logger.info("Creating new banner for page: {}", textData.getPageName());
        BannerEntity entity = new BannerEntity();
        entity.setPageName(textData.getPageName());
        entity.setHeader(textData.getHeader());
        entity.setText(textData.getText());

        // Handle file uploads
        List<byte[]> bannerFileOneBytes = new ArrayList<>();
        if (bannerFileOne != null) {
            for (var file : bannerFileOne) {
                if (file != null && !file.isEmpty()) {
                    bannerFileOneBytes.add(file.getBytes());
                }
            }
        }
        entity.setBannerFileOne(bannerFileOneBytes);

        if (bannerFileTwo != null && !bannerFileTwo.isEmpty()) {
            entity.setBannerFileTwo(bannerFileTwo.getBytes());
        }
        if (bannerFileThree != null && !bannerFileThree.isEmpty()) {
            entity.setBannerFileThree(bannerFileThree.getBytes());
        }
        if (bannerFileFour != null && !bannerFileFour.isEmpty()) {
            entity.setBannerFileFour(bannerFileFour.getBytes());
        }

        BannerEntity savedEntity = bannerRepository.save(entity);
        logger.info("Banner created with ID: {}", savedEntity.getBannerId());
        return convertToResponseDto(savedEntity);
    }

    @Override
    public BannerResponseDto getBannerById(Long bannerId) throws Exception {
        logger.info("Fetching banner with ID: {}", bannerId);
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));
        return convertToResponseDto(entity);
    }

    @Override
    public List<BannerResponseDto> getAllBanners() {
        logger.info("Fetching all banners");
        return bannerRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BannerResponseDto updateBanner(Long bannerId, BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception {
        logger.info("Updating banner with ID: {}", bannerId);
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));

        entity.setPageName(textData.getPageName());
        entity.setHeader(textData.getHeader());
        entity.setText(textData.getText());

        // Handle file updates
        List<byte[]> bannerFileOneBytes = new ArrayList<>();
        if (bannerFileOne != null) {
            for (var file : bannerFileOne) {
                if (file != null && !file.isEmpty()) {
                    bannerFileOneBytes.add(file.getBytes());
                }
            }
        }
        entity.setBannerFileOne(bannerFileOneBytes);

        if (bannerFileTwo != null && !bannerFileTwo.isEmpty()) {
            entity.setBannerFileTwo(bannerFileTwo.getBytes());
        } else {
            entity.setBannerFileTwo(null); // Allow clearing the file
        }
        if (bannerFileThree != null && !bannerFileThree.isEmpty()) {
            entity.setBannerFileThree(bannerFileThree.getBytes());
        } else {
            entity.setBannerFileThree(null); // Allow clearing the file
        }
        if (bannerFileFour != null && !bannerFileFour.isEmpty()) {
            entity.setBannerFileFour(bannerFileFour.getBytes());
        } else {
            entity.setBannerFileFour(null); // Allow clearing the file
        }

        BannerEntity updatedEntity = bannerRepository.save(entity);
        logger.info("Banner updated with ID: {}", updatedEntity.getBannerId());
        return convertToResponseDto(updatedEntity);
    }

    @Override
    public BannerResponseDto patchBanner(Long bannerId, BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception {
        logger.info("Patching banner with ID: {}", bannerId);
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));

        // Partial update: Only set if provided
        if (textData != null) {
            if (textData.getPageName() != null) entity.setPageName(textData.getPageName());
            if (textData.getHeader() != null) entity.setHeader(textData.getHeader());
            if (textData.getText() != null) entity.setText(textData.getText());
        }

        // Partial file updates
        if (bannerFileOne != null) {
            List<byte[]> bannerFileOneBytes = new ArrayList<>();
            for (var file : bannerFileOne) {
                if (file != null && !file.isEmpty()) {
                    bannerFileOneBytes.add(file.getBytes());
                }
            }
            entity.setBannerFileOne(bannerFileOneBytes);
        }

        if (bannerFileTwo != null) {
            if (!bannerFileTwo.isEmpty()) {
                entity.setBannerFileTwo(bannerFileTwo.getBytes());
            } else {
                entity.setBannerFileTwo(null); // Clear if empty file sent
            }
        }
        if (bannerFileThree != null) {
            if (!bannerFileThree.isEmpty()) {
                entity.setBannerFileThree(bannerFileThree.getBytes());
            } else {
                entity.setBannerFileThree(null);
            }
        }
        if (bannerFileFour != null) {
            if (!bannerFileFour.isEmpty()) {
                entity.setBannerFileFour(bannerFileFour.getBytes());
            } else {
                entity.setBannerFileFour(null);
            }
        }

        BannerEntity updatedEntity = bannerRepository.save(entity);
        logger.info("Banner patched with ID: {}", updatedEntity.getBannerId());
        return convertToResponseDto(updatedEntity);
    }

    @Override
    public void deleteBanner(Long bannerId) throws Exception {
        logger.info("Deleting banner with ID: {}", bannerId);
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));
        bannerRepository.delete(entity);
        logger.info("Banner deleted with ID: {}", bannerId);
    }

    // New: Get subimage by index
    public byte[] getSubImage(Long bannerId, int index) throws Exception {
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));
        List<byte[]> files = entity.getBannerFileOne();
        if (index < 0 || index >= files.size() || files.get(index) == null) {
            return null;
        }
        return files.get(index);
    }

    // New: Get single banner file by type
    public byte[] getBannerFile(Long bannerId, String type) throws Exception {
        BannerEntity entity = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + bannerId));
        return switch (type) {
            case "two" -> entity.getBannerFileTwo();
            case "three" -> entity.getBannerFileThree();
            case "four" -> entity.getBannerFileFour();
            default -> null;
        };
    }

    private BannerResponseDto convertToResponseDto(BannerEntity entity) {
        BannerResponseDto responseDto = new BannerResponseDto();
        responseDto.setBannerId(entity.getBannerId());
        responseDto.setPageName(entity.getPageName());
        responseDto.setHeader(entity.getHeader());
        responseDto.setText(entity.getText());

        // Generate URLs instead of Base64
        List<String> bannerFileOneUrls = new ArrayList<>();
        if (entity.getBannerFileOne() != null) {
            for (int i = 0; i < entity.getBannerFileOne().size(); i++) {
                if (entity.getBannerFileOne().get(i) != null) {
                    bannerFileOneUrls.add("/api/banners/" + entity.getBannerId() + "/subimage/" + i);
                }
            }
        }
        responseDto.setBannerFileOne(bannerFileOneUrls);

        Long id = entity.getBannerId();
        if (entity.getBannerFileTwo() != null) {
            responseDto.setBannerFileTwo("/api/banners/" + id + "/bannerFileTwo");
        }
        if (entity.getBannerFileThree() != null) {
            responseDto.setBannerFileThree("/api/banners/" + id + "/bannerFileThree");
        }
        if (entity.getBannerFileFour() != null) {
            responseDto.setBannerFileFour("/api/banners/" + id + "/bannerFileFour");
        }

        return responseDto;
    }
}