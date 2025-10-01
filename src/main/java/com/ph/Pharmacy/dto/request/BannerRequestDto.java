package com.ph.Pharmacy.dto.request;

import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

public class BannerRequestDto {
    private String pageName;
    private String header;
    private String text;
    private List<MultipartFile> bannerFileOne = new ArrayList<>();
    private MultipartFile bannerFileTwo;
    private MultipartFile bannerFileThree;
    private MultipartFile bannerFileFour;

    // Default constructor
    public BannerRequestDto() {}

    // Constructor with parameters
    public BannerRequestDto(String pageName, String header, String text) {
        this.pageName = pageName;
        this.header = header;
        this.text = text;
    }

    // Getters and Setters
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MultipartFile> getBannerFileOne() {
        return bannerFileOne;
    }

    public void setBannerFileOne(List<MultipartFile> bannerFileOne) {
        this.bannerFileOne = bannerFileOne;
    }

    public MultipartFile getBannerFileTwo() {
        return bannerFileTwo;
    }

    public void setBannerFileTwo(MultipartFile bannerFileTwo) {
        this.bannerFileTwo = bannerFileTwo;
    }

    public MultipartFile getBannerFileThree() {
        return bannerFileThree;
    }

    public void setBannerFileThree(MultipartFile bannerFileThree) {
        this.bannerFileThree = bannerFileThree;
    }

    public MultipartFile getBannerFileFour() {
        return bannerFileFour;
    }

    public void setBannerFileFour(MultipartFile bannerFileFour) {
        this.bannerFileFour = bannerFileFour;
    }
}
