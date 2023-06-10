package com.example.webPOS.dto;

public class Inventory {
    private String productId;
    private int quantity;
    private String storeName;

    public String getProductId() {
        return productId;
    }

    public Inventory(){}

    public Inventory(String productId, int quantity, String storeName) {
        this.productId = productId;
        this.quantity = quantity;
        this.storeName = storeName;
    }

    public void setProductId(String productId) {
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
