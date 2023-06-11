package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaleService  implements TradeService {
    TradeLogDAO tradeLogDAO;
    InventoryDAO inventoryDAO;

    @Autowired
    public SaleService(TradeLogDAO tradeLogDAO, InventoryDAO inventoryDAO) {
        this.tradeLogDAO = tradeLogDAO;
        this.inventoryDAO = inventoryDAO;
    }

    @Override
    public List<Inventory> showInventory(String storeName) {
        return inventoryDAO.findAll(storeName);
    }


}
