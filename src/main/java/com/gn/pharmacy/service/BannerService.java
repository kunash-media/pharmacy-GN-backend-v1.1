package com.gn.pharmacy.service;

import com.gn.pharmacy.dto.request.BannerTextRequestDto;
import com.gn.pharmacy.dto.response.BannerResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BannerService {

    BannerResponseDto createBanner(BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception;

    BannerResponseDto getBannerById(Long bannerId) throws Exception;

    List<BannerResponseDto> getAllBanners() throws Exception;

    BannerResponseDto updateBanner(Long bannerId, BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception;

    BannerResponseDto patchBanner(Long bannerId, BannerTextRequestDto textData, List<MultipartFile> bannerFileOne, MultipartFile bannerFileTwo, MultipartFile bannerFileThree, MultipartFile bannerFileFour) throws Exception;

    void deleteBanner(Long bannerId) throws Exception;

    byte[] getSubImage(Long bannerId, int index) throws Exception;

    byte[] getBannerFile(Long bannerId, String type) throws Exception;
}
