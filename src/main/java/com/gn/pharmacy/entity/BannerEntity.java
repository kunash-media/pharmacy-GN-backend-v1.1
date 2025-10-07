package com.gn.pharmacy.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banners_table")
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "header")
    private String header;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @ElementCollection
    @CollectionTable(name = "banner_file_one", joinColumns = @JoinColumn(name = "banner_id"))
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private List<byte[]> bannerFileOne = new ArrayList<>();

    @Column(name = "banner_file_two", columnDefinition = "LONGBLOB")
    private byte[] bannerFileTwo;

    @Column(name = "banner_file_three", columnDefinition = "LONGBLOB")
    private byte[] bannerFileThree;

    @Column(name = "banner_file_four", columnDefinition = "LONGBLOB")
    private byte[] bannerFileFour;

    // Default constructor
    public BannerEntity() {}


    public BannerEntity(Long bannerId, String pageName, String header, String text,
                        List<byte[]> bannerFileOne, byte[] bannerFileTwo,
                        byte[] bannerFileThree, byte[] bannerFileFour) {
        this.bannerId = bannerId;
        this.pageName = pageName;
        this.header = header;
        this.text = text;
        this.bannerFileOne = bannerFileOne;
        this.bannerFileTwo = bannerFileTwo;
        this.bannerFileThree = bannerFileThree;
        this.bannerFileFour = bannerFileFour;
    }

    // Getters and Setters


    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
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

    public List<byte[]> getBannerFileOne() {
        return bannerFileOne;
    }

    public void setBannerFileOne(List<byte[]> bannerFileOne) {
        this.bannerFileOne = bannerFileOne;
    }

    public byte[] getBannerFileTwo() {
        return bannerFileTwo;
    }

    public void setBannerFileTwo(byte[] bannerFileTwo) {
        this.bannerFileTwo = bannerFileTwo;
    }

    public byte[] getBannerFileThree() {
        return bannerFileThree;
    }

    public void setBannerFileThree(byte[] bannerFileThree) {
        this.bannerFileThree = bannerFileThree;
    }

    public byte[] getBannerFileFour() {
        return bannerFileFour;
    }

    public void setBannerFileFour(byte[] bannerFileFour) {
        this.bannerFileFour = bannerFileFour;
    }



    @Override
    public String toString() {
        return "BannerEntity{" +
                "bannerId=" + bannerId +
                ", pageName='" + pageName + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", hasImage1=" + (bannerFileOne != null) +
                ", hasImage2=" + (bannerFileTwo != null) +
                ", hasImage3=" + (bannerFileThree != null) +
                ", hasImage4=" + (bannerFileFour != null) +
                '}';
    }
}