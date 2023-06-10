package com.example.webPOS.dto;


import java.time.LocalDateTime;

public class TradeLog {
    private int id;
    private String productId;
    private LocalDateTime tradeDate;
    private int quantityTraded;
    private long totalPrice;
    private String state;
    private String storeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDateTime tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getQuantityTraded() {
        return quantityTraded;
    }

    public void setQuantityTraded(int quantityTraded) {
        this.quantityTraded = quantityTraded;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}