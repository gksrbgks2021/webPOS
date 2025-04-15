package com.example.webPOS.vo;


import java.time.LocalDateTime;

public class TradeLog {
    private Long id; //자동 증가.
    private Long productId; //이미 있는 id만 가져와야함.
    private LocalDateTime tradeDate;//거래날짜
    private int quantityTraded;
    private long totalPrice;
    private String state;
    private String storeName;

    public TradeLog(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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