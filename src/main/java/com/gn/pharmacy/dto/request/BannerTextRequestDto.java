package com.gn.pharmacy.dto.request;

public class BannerTextRequestDto {

    private String pageName;
    private String header;
    private String text;

    // Default constructor
    public BannerTextRequestDto() {}

    // Getters and setters
    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }

    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
