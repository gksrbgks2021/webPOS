package com.example.webPOS.service;

import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.Product;

import java.util.List;

public interface TradeService {

    List<Inventory> showInventory(String storeName);
    List<Product> showProduct();
}
