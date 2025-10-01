package com.ph.Pharmacy.service.serviceImpl;
import com.ph.Pharmacy.dto.request.BannerRequestDto;
import com.ph.Pharmacy.dto.response.BannerResponseDto;
import com.ph.Pharmacy.entity.BannerEntity;
import com.ph.Pharmacy.repository.BannerRepository;
import com.ph.Pharmacy.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public BannerResponseDto createBanner(BannerRequestDto requestDto) throws Exception {
        logger.info("Creating new banner for page: {}", requestDto.getPageName());
        BannerEntity entity = new BannerEntity();
        entity.setPageName(requestDto.getPageName());
        entity.setHeader(requestDto.getHeader());
        entity.setText(requestDto.getText());

        // Handle file uploads
        List<byte[]> bannerFileOne = new ArrayList<>();
        if (requestDto.getBannerFileOne() != null) {
            for (var file : requestDto.getBannerFileOne()) {
                if (file != null && !file.isEmpty()) {
                    bannerFileOne.add(file.getBytes());
                }
            }
        }
        entity.setBannerFileOne(bannerFileOne);

        if (requestDto.getBannerFileTwo() != null && !requestDto.getBannerFileTwo().isEmpty()) {
            entity.setBannerFileTwo(requestDto.getBannerFileTwo().getBytes());
        }
        if (requestDto.getBannerFileThree() != null && !requestDto.getBannerFileThree().isEmpty()) {
            entity.setBannerFileThree(requestDto.getBannerFileThree().getBytes());
        }
        if (requestDto.getBannerFileFour() != null && !requestDto.getBannerFileFour().isEmpty()) {
            entity.setBannerFileFour(requestDto.getBannerFileFour().getBytes());
        }

        BannerEntity savedEntity = bannerRepository.save(entity);
        logger.info("Banner created with ID: {}", savedEntity.getId());
        return convertToResponseDto(savedEntity);
    }

    @Override
    public BannerResponseDto getBannerById(Long id) throws Exception {
        logger.info("Fetching banner with ID: {}", id);
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + id));
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
    public BannerResponseDto updateBanner(Long id, BannerRequestDto requestDto) throws Exception {
        logger.info("Updating banner with ID: {}", id);
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + id));

        entity.setPageName(requestDto.getPageName());
        entity.setHeader(requestDto.getHeader());
        entity.setText(requestDto.getText());

        // Handle file updates
        List<byte[]> bannerFileOne = new ArrayList<>();
        if (requestDto.getBannerFileOne() != null) {
            for (var file : requestDto.getBannerFileOne()) {
                if (file != null && !file.isEmpty()) {
                    bannerFileOne.add(file.getBytes());
                }
            }
        }
        entity.setBannerFileOne(bannerFileOne);

        if (requestDto.getBannerFileTwo() != null && !requestDto.getBannerFileTwo().isEmpty()) {
            entity.setBannerFileTwo(requestDto.getBannerFileTwo().getBytes());
        } else {
            entity.setBannerFileTwo(null); // Allow clearing the file
        }
        if (requestDto.getBannerFileThree() != null && !requestDto.getBannerFileThree().isEmpty()) {
            entity.setBannerFileThree(requestDto.getBannerFileThree().getBytes());
        } else {
            entity.setBannerFileThree(null); // Allow clearing the file
        }
        if (requestDto.getBannerFileFour() != null && !requestDto.getBannerFileFour().isEmpty()) {
            entity.setBannerFileFour(requestDto.getBannerFileFour().getBytes());
        } else {
            entity.setBannerFileFour(null); // Allow clearing the file
        }

        BannerEntity updatedEntity = bannerRepository.save(entity);
        logger.info("Banner updated with ID: {}", updatedEntity.getId());
        return convertToResponseDto(updatedEntity);
    }

    @Override
    public void deleteBanner(Long id) throws Exception {
        logger.info("Deleting banner with ID: {}", id);
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new Exception("Banner not found with ID: " + id));
        bannerRepository.delete(entity);
        logger.info("Banner deleted with ID: {}", id);
    }

    private BannerResponseDto convertToResponseDto(BannerEntity entity) {
        BannerResponseDto responseDto = new BannerResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setPageName(entity.getPageName());
        responseDto.setHeader(entity.getHeader());
        responseDto.setText(entity.getText());

        // Convert byte[] to Base64 for response
        List<String> bannerFileOneBase64 = new ArrayList<>();
        if (entity.getBannerFileOne() != null) {
            for (byte[] file : entity.getBannerFileOne()) {
                if (file != null) {
                    bannerFileOneBase64.add(Base64.getEncoder().encodeToString(file));
                }
            }
        }
        responseDto.setBannerFileOne(bannerFileOneBase64);

        if (entity.getBannerFileTwo() != null) {
            responseDto.setBannerFileTwo(Base64.getEncoder().encodeToString(entity.getBannerFileTwo()));
        }
        if (entity.getBannerFileThree() != null) {
            responseDto.setBannerFileThree(Base64.getEncoder().encodeToString(entity.getBannerFileThree()));
        }
        if (entity.getBannerFileFour() != null) {
            responseDto.setBannerFileFour(Base64.getEncoder().encodeToString(entity.getBannerFileFour()));
        }

        return responseDto;
    }
}
