package com.example.webPOS.dto;

public class Inventory {
    private Long productId;
    private int quantity;
    private String storeName;

    public Long getProductId() {
        return productId;
    }

    public Inventory(){}

    public Inventory(Long productId, int quantity, String storeName) {
        this.productId = productId;
        this.quantity = quantity;
        this.storeName = storeName;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
