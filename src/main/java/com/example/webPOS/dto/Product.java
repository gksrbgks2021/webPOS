package com.example.webPOS.dto;

public class Product {
    private long productId;
    private long netPrice;
    private long costPrice;
    private String name;

    public Product() {}

    public Product(long netPrice, long costPrice, String name) {
        this.netPrice = netPrice;
        this.costPrice = costPrice;
        this.name = name;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
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