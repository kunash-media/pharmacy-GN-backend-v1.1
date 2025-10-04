package com.gn.pharmacy.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BannerResponseDto {
    private Long id;
    private String pageName;
    private String header;
    private String text;
    private List<String> bannerFileOne = new ArrayList<>(); // Base64 encoded strings
    private String bannerFileTwo; // Base64 encoded string
    private String bannerFileThree; // Base64 encoded string
    private String bannerFileFour; // Base64 encoded string

    // Default constructor
    public BannerResponseDto() {}

    // Constructor with parameters
    public BannerResponseDto(Long id, String pageName, String header, String text) {
        this.id = id;
        this.pageName = pageName;
        this.header = header;
        this.text = text;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getBannerFileOne() {
        return bannerFileOne;
    }

    public void setBannerFileOne(List<String> bannerFileOne) {
        this.bannerFileOne = bannerFileOne;
    }

    public String getBannerFileTwo() {
        return bannerFileTwo;
    }

    public void setBannerFileTwo(String bannerFileTwo) {
        this.bannerFileTwo = bannerFileTwo;
    }

    public String getBannerFileThree() {
        return bannerFileThree;
    }

    public void setBannerFileThree(String bannerFileThree) {
        this.bannerFileThree = bannerFileThree;
    }

    public String getBannerFileFour() {
        return bannerFileFour;
    }

    public void setBannerFileFour(String bannerFileFour) {
        this.bannerFileFour = bannerFileFour;
    }
}
