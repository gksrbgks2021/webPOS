package com.example.webPOS.dto;

public class ProductDTO {
    private String productId;
    private long netPrice;
    private long costPrice;
    private String name;

    ProductDTO() {}

    ProductDTO(String productId, long netPrice, long costPrice, String name) {
        this.productId = productId;
        this.netPrice = netPrice;
        this.costPrice = costPrice;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(long netPrice) {
        this.netPrice = netPrice;
    }

    public long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(long costPrice) {
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}