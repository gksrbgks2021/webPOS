package com.example.webPOS.service;

import com.example.webPOS.dto.Inventory;

import java.util.List;

public interface TradeService {

    List<Inventory> showInventory(String storeName);

}
