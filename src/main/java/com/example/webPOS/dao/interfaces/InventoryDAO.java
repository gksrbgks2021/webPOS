package com.example.webPOS.dao.interfaces;

import java.util.List;

import com.example.webPOS.dto.Inventory;

public interface InventoryDAO {
    List<Inventory> findAll(String storeName);
    void update(Inventory inventory);
}
