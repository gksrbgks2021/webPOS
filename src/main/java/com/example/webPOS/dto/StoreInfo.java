package com.example.webPOS.dto;

public class StoreInfo {
    private String managerName;
    private String storeName;

    public StoreInfo(){}

    public StoreInfo(String managerName, String storeName) {
        this.managerName = managerName;
        this.storeName = storeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
