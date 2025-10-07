package com.gn.pharmacy.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BannerResponseDto {

    private Long bannerId;
    private String pageName;
    private String header;
    private String text;
    private List<String> bannerFileOne = new ArrayList<>(); // URLs
    private String bannerFileTwo; // URL
    private String bannerFileThree; // URL
    private String bannerFileFour; // URL

    // Default constructor
    public BannerResponseDto() {}

    // Getters and setters (updated for URLs)
    public Long getBannerId() { return bannerId; }
    public void setBannerId(Long bannerId) { this.bannerId = bannerId; }

    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }

    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<String> getBannerFileOne() { return bannerFileOne; }
    public void setBannerFileOne(List<String> bannerFileOne) { this.bannerFileOne = bannerFileOne; }

    public String getBannerFileTwo() { return bannerFileTwo; }
    public void setBannerFileTwo(String bannerFileTwo) { this.bannerFileTwo = bannerFileTwo; }

    public String getBannerFileThree() { return bannerFileThree; }
    public void setBannerFileThree(String bannerFileThree) { this.bannerFileThree = bannerFileThree; }

    public String getBannerFileFour() { return bannerFileFour; }
    public void setBannerFileFour(String bannerFileFour) { this.bannerFileFour = bannerFileFour; }
}